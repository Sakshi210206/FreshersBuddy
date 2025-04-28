package com.aavidsoft.freshersbuddy.utils.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("image_url")
fun loadImageToImageView(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView)
        .load(imageUrl)
        .error(com.aavidsoft.freshersbuddy.R.drawable.ic_img)
        .placeholder(com.aavidsoft.freshersbuddy.R.drawable.ic_img)
        .into(imageView)
}

@BindingAdapter("image_urls")
fun loadImage(view: ImageView, imageUrls: String?) {
    Glide.with(view.context)
        .load(imageUrls)
        .placeholder(com.aavidsoft.freshersbuddy.R.drawable.ic_img)
        .error(com.aavidsoft.freshersbuddy.R.drawable.ic_img)
        .into(view)
}
@BindingAdapter("imageurl")
fun loadImg(view: ImageView,imageUrl : String?){
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(com.aavidsoft.freshersbuddy.R.drawable.ic_c)
        .error(com.aavidsoft.freshersbuddy.R.drawable.ic_img)
        .into(view)
}

