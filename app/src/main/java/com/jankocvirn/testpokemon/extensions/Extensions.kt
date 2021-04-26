package com.jankocvirn.testpokemon.extensions

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jankocvirn.testpokemon.R

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .error(R.color.black)
        .placeholder(R.color.black)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}
