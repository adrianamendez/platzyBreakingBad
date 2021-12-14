package com.example.basicworktest.denise.mendez.view.imageLoadingCaching

import android.widget.ImageView
import com.google.android.material.imageview.ShapeableImageView

interface LoadImageInterface<L> {

    fun loadImage(uri: String, target: ShapeableImageView)

    fun loadImage(uri: String, target: ShapeableImageView, listener: L)
}