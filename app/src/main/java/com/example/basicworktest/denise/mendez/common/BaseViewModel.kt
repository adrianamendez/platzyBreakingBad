package com.example.basicworktest.denise.mendez.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.basicworktest.denise.domain.exceptions.ErrorResponse
import com.example.basicworktest.denise.domain.messagesexception.MessageException
import com.example.basicworktest.denise.domain.messagesexception.MessageExceptionInfo
import com.example.basicworktest.denise.mendez.utils.asLiveData
import com.example.basicworktest.denise.mendez.utils.lifecycle.NavigationEvents

open class BaseViewModel(private val messageException: MessageException) : ViewModel() {

    private val _hasInternetConnection = SingleLiveEvent<Boolean>()
    val hasInternetConnection get() = _hasInternetConnection.asLiveData()

    protected val errorMessageSingle = SingleLiveEvent<MessageExceptionInfo>()
    val errorMessage: LiveData<MessageExceptionInfo> = errorMessageSingle

    protected val _navigationEvent = SingleLiveEvent<NavigationEvents>()
    val navigationEvent get() = _navigationEvent.asLiveData()

    fun showError(errorResponse: ErrorResponse) {
        messageException.process(Throwable()).run {
            errorMessageSingle.postValue(this)
        }
    }
}