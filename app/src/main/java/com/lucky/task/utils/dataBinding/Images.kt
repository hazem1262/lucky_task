package com.lucky.task.utils.dataBinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lucky.task.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.image_loader)
        .error(R.mipmap.ic_launcher)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .dontAnimate()
        .dontTransform()
    Glide.with(view.context)
        .load(imageUrl)
        .apply(options)
        .into(view)
}