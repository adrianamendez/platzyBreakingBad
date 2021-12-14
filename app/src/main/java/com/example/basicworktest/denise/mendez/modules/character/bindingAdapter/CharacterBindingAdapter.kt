package com.example.basicworktest.denise.mendez.modules.character.bindingAdapter

import android.view.View
import android.view.View.*
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.example.basicworktest.denise.mendez.R
import com.example.basicworktest.denise.mendez.common.ScopedViewModel
import com.example.basicworktest.denise.mendez.modules.character.entities.CharacterBreakingBadUi
import com.example.basicworktest.denise.mendez.modules.character.entities.EpisodesBreakingBadUi
import com.example.basicworktest.denise.mendez.utils.hideKeyboard
import com.example.basicworktest.denise.mendez.view.imageLoadingCaching.ImageLoader
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("loadImageUrl")
fun ShapeableImageView.loadImage(url: String?) {
    url?.let { ImageLoader(context).loadImage(url, this) }
}

@BindingAdapter("isFavourite")
fun ImageView.isFavourite(character: CharacterBreakingBadUi?) {
    character?.let {
        if (it.isFavourite) {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context, // Context
                    R.drawable.ic_favourite_fill// Drawable
                )
            )
        } else {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context, // Context
                    R.drawable.ic_favourite_empty// Drawable
                )
            )
        }
    }
}

@BindingAdapter("isFavouriteLottie")
fun LottieAnimationView.isFavouriteLottie(isFavourite: Boolean) {
    visibility = if (isFavourite) VISIBLE
    else INVISIBLE
}

@BindingAdapter("uiLoading")
fun View.uiLoading(uiState: ScopedViewModel.UiModel?) {
    visibility = if (uiState is ScopedViewModel.UiModel.Loading) VISIBLE
    else GONE
}

@BindingAdapter("uiErrorState")
fun View.uiErrorState(uiState: ScopedViewModel.UiModel?) {
    this.hideKeyboard()
    visibility = if (uiState is ScopedViewModel.UiModel.ErrorState) VISIBLE
    else GONE
}

@BindingAdapter("uiNoInternetState")
fun View.uiNoInternetState(uiState: ScopedViewModel.UiModel?) {
    this.hideKeyboard()
    visibility = if (uiState is ScopedViewModel.UiModel.NoInternetState) VISIBLE
    else GONE
}

@BindingAdapter("uiEmptyState")
fun View.uiEmptyState(uiState: ScopedViewModel.UiModel?) {
    this.hideKeyboard()
    visibility = if (uiState is ScopedViewModel.UiModel.EmptyState) VISIBLE
    else GONE
}

@BindingAdapter("uiEpisodesView")
fun View.uiEpisodesView(episodesBreakingBadUi: EpisodesBreakingBadUi) {
    this.hideKeyboard()
    visibility = if (episodesBreakingBadUi.episodeId != -1) VISIBLE
    else GONE
}
