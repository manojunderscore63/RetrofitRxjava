package com.example.retrofitrxjava

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(url: String){
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("backgroundColor2")
fun TextView.backgroundColor2(num: Int){
    if (num==1){
        this.setBackgroundColor(resources.getColor(R.color.teal_200))
    }else{
        this.setBackgroundColor(resources.getColor(R.color.white))
    }
}

@BindingAdapter("drawable2")
fun imageView_setImageDrawable(view: ImageView, drawable: Drawable?) {
    if (view.drawable != drawable) {
        view.setImageDrawable(drawable)
    }
}