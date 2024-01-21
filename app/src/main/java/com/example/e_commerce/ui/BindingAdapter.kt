package com.example.e_commerce.ui

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun bindImage(image : ImageView, url : String){
    Glide.with(image)
        .load(url)
        .into(image)
}
@BindingAdapter("Error")
fun setError(view: TextView, message:String){
    view.error = message
}
@BindingAdapter("loading")
fun showProgressBar(progressBar: ProgressBar, isVisible:Boolean){
    if (isVisible){
        progressBar.visibility = View.VISIBLE
    }else{
        progressBar.visibility = View.GONE
    }
}
@BindingAdapter("setText")
fun convertIntToString(textView: TextView,value:Int){
    textView.text = value.toString()
}
