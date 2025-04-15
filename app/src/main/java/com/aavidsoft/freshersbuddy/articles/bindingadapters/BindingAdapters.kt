package com.aavidsoft.freshersbuddy.articles.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide



@BindingAdapter("image_url")
fun loadImageToImageView(imageView: ImageView,imageUrl:String?){
    Glide.with(imageView)
        .load(imageUrl)
        .error(com.aavidsoft.freshersbuddy.R.drawable.ic_img)
        .placeholder(com.aavidsoft.freshersbuddy.R.drawable.ic_img)
        .into(imageView)

}

