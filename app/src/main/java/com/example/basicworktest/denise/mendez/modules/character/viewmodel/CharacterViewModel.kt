package com.example.basicworktest.denise.mendez.modules.character.viewmodel

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basicworktest.denise.domain.entities.CharacterBreakingBadEntity
import com.example.basicworktest.denise.domain.exceptions.onError
import com.example.basicworktest.denise.domain.exceptions.onSuccess
import com.example.basicworktest.denise.domain.messagesexception.MessageException
import com.example.basicworktest.denise.mendez.common.BaseViewModel
import com.example.basicworktest.denise.mendez.common.ScopedViewModel
import com.example.basicworktest.denise.mendez.common.SingleLiveEvent
import com.example.basicworktest.denise.mendez.modules.character.entities.CharacterBreakingBadUi
import com.example.basicworktest.denise.mendez.modules.character.entities.EpisodesBreakingBadUi
import com.example.basicworktest.denise.mendez.modules.character.entities.mapToDomain
import com.example.basicworktest.denise.mendez.modules.character.view.CharacterFragmentDirections
import com.example.basicworktest.denise.mendez.network.InternetAvailability
import com.example.basicworktest.denise.mendez.network.NetworkStatus
import com.example.basicworktest.denise.mendez.utils.Constant.LIMIT
import com.example.basicworktest.denise.mendez.utils.Constant.OFFSET
import com.example.basicworktest.denise.mendez.utils.Constant.RANDOM_END
import com.example.basicworktest.denise.mendez.utils.Constant.RANDOM_START
import com.example.basicworktest.denise.mendez.utils.CoroutineContextProvider
import com.example.basicworktest.denise.mendez.utils.asLiveData
import com.example.basicworktest.denise.mendez.utils.concatenate
import com.example.basicworktest.denise.mendez.utils.lifecycle.NavigationToDirectionEvent
import com.example.basicworktest.denise.usecases.breakingbad.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

/**
 * Koin injection with adding the context provider coroutine as google recommends in order to achieve the UT on the real scope
 */
class CharacterViewModel(
    private val useCase: UseCase,
    messageException: MessageException,
    contextProvider: CoroutineContextProvider
) : BaseViewModel(messageException) {
    /**
     * Variable declaration
     */
    private val _model = SingleLiveEvent<ScopedViewModel.UiModel>()
    val model get() = _model.asLiveData()
    private lateinit var job: Job
    private lateinit var jobService: Job
    var firstDefaultInternetCall: Boolean = true
    private val _character = MutableLiveData<CharacterBreakingBadUi>()
    val character get() = _character.asLiveData()
    val characterList: LiveData<List<CharacterBreakingBadUi>> get() = _characterList
    private var _characterList = MutableLiveData(listOf<CharacterBreakingBadUi>())
        private set
    val episode: LiveData<EpisodesBreakingBadUi> get() = _episode
    private var _episode = MutableLiveData(EpisodesBreakingBadUi())
        private set
    var updateFavorite: Boolean = false
    private val _notifyFavourite = MutableLiveData<Boolean>()
    val notifyFavourite get() = _notifyFavourite.asLiveData()
    private var _dataList: List<CharacterBreakingBadUi> = listOf()
    val dataList get() = _dataList
    private val _notFoundList = MutableLiveData<Boolean>()
    val notFoundList get() = _notFoundList
    private var _internetVisibility = MutableLiveData(GONE)
    val internetVisibility: LiveData<Int> get() = _internetVisibility
    private var _errorVisibility = MutableLiveData(GONE)
    val errorVisibility: LiveData<Int> get() = _errorVisibility
    private val ioContext: CoroutineContext = (contextProvider.IO)
    private var currentLimit = LIMIT
    private var currentOffset = OFFSET

    /**
     * View initialization react to internet availability
     */
    fun init() {
        job = viewModelScope.launch(ioContext) {
            getCharacterList()
            if (InternetAvailability.check()) showLoaderUiModel()
            else showNoInternetUiModel()
        }
    }

    /**
     * Getting the character list, with share model is a must valida if the data is not loaded,
     * I could add the offset and limit by 15 rows, showing the loader
     * and a concatenate method to add the old response with the new one
     * The API documentation doesn't not saying anything about error response
     * neither a total count for pagination
     */
    fun getCharacterList() {
        showLoaderUiModel()
        if (::jobService.isInitialized) jobService.cancel()
        jobService = viewModelScope.launch(ioContext) {
            useCase.getCharacters(
                currentOffset,
                currentLimit
            ).onSuccess {
                if (it.isNullOrEmpty()) {
                    _errorVisibility.postValue(GONE)
                    _notFoundList.postValue(true)
                    hideLoaderUiModel()
                } else {
                    currentOffset += currentLimit
                    setCharacterListList(it)
                }
            }.onError {
                if (this.failure !is CancellationException) {
                    showError(this)
                    if (dataList.isNullOrEmpty())
                        _errorVisibility.postValue(VISIBLE)
                }
            }
        }
    }

    /**
     * Setting the character list, concatenate the static List with the new one,
     * updating the view and hiding the loader
     */
    private fun setCharacterListList(
        characterList: List<CharacterBreakingBadEntity>
    ) {

        val newResults = characterList.map {
            CharacterBreakingBadUi.mapFromDomain(it)
        }
        _dataList = concatenate(
            _dataList,
            newResults
        )
        updateView(false)
        hideLoaderUiModel()
    }

    private fun updateView(notifyFavourite: Boolean) {
        if (::job.isInitialized) job.cancel()
        job = viewModelScope.launch(ioContext) {
            updateFavouriteList(useCase.getFavouriteItems(), notifyFavourite)
        }
    }

    /**
     * updating the server list with the favourite db list, looking fot the common id and sorting by favourite
     */
    private fun updateFavouriteList(
        getFavouriteItems: List<CharacterBreakingBadEntity>,
        notifyFavourite: Boolean
    ) {
        val newFavourite = getFavouriteItems.map {
            CharacterBreakingBadUi.mapFromDomain(it)
        }
        updateFavorite = true

        if (!newFavourite.isNullOrEmpty()) {
            val dataList = _dataList.map { firstListElement ->
                newFavourite
                    .filter { it.charId == firstListElement.charId }
                    .lastOrNull()
                    .let {
                        firstListElement.copy(isFavourite = it?.isFavourite ?: false)
                    }
            }.sortedByDescending { it.isFavourite }
            _characterList.postValue(dataList)
        } else {
            _characterList.postValue(dataList)
        }
        notifyFavourite(notifyFavourite)
    }


    fun setCharacter(character: CharacterBreakingBadUi) {
        _character.value = character
        getEpisodes()
    }

    fun saveFavourite(item: CharacterBreakingBadUi) {
        if (::job.isInitialized) job.cancel()
        job = viewModelScope.launch(ioContext) {
            useCase.saveFavouriteItems(item.mapToDomain())
            updateFavouriteList(useCase.getFavouriteItems(), true)
        }
    }

    fun deleteFavourite(item: CharacterBreakingBadUi) {
        if (::job.isInitialized) job.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteFavouriteItems(item.charId.toString())
            updateFavouriteList(useCase.getFavouriteItems(), false)
        }
    }

    private fun getEpisodes() {
        showLoaderUiModel()
        if (::jobService.isInitialized) jobService.cancel()
        jobService = viewModelScope.launch(ioContext) {
            useCase.getEpisodes(
                (RANDOM_START..RANDOM_END).random()
            ).onSuccess {
                if (!it.isNullOrEmpty()) {
                    _episode.postValue(EpisodesBreakingBadUi.mapFromDomain(it.first()))
                    hideLoaderUiModel()
                }
            }.onError {
                showError(this)
            }
        }
    }

    /**
     * function for  reactive view for internet availability
     */
    fun showHideInternetConnection(networkStatus: NetworkStatus) {
        if (!firstDefaultInternetCall)
            when (networkStatus) {
                NetworkStatus.Available -> showEmptyStateUiModel()
                NetworkStatus.Unavailable -> showNoInternetUiModel()
            }
        else {
            firstDefaultInternetCall = false
        }
    }

    /**
     * go to next fragment on card click or item selection event, reset the favourite notification
     */
    fun onItemSelected(item: CharacterBreakingBadUi) {
        _navigationEvent.value =
            NavigationToDirectionEvent(
                CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(item)
            )
        notifyFavourite(false)
    }

    /**
     * set the favourite status
     */
    private fun notifyFavourite(notify: Boolean) {
        _notifyFavourite.postValue(notify)
    }

    /**
     * Reactive view Ui Model to manage different view states
     */

    private fun showEmptyStateUiModel() = _model.postValue(ScopedViewModel.UiModel.EmptyState)

    private fun hideLoaderUiModel() = _model.postValue(ScopedViewModel.UiModel.HideLoader)

    private fun showLoaderUiModel() = _model.postValue(ScopedViewModel.UiModel.Loading)

    private fun showNoInternetUiModel() = _model.postValue(ScopedViewModel.UiModel.NoInternetState)

    /**
     * Canceling coroutine jobs
     */
    override fun onCleared() {
        super.onCleared()
        job.cancel()
        jobService.cancel()
    }
}