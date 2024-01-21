package com.example.e_commerce.ui

import android.app.Activity
import android.app.AlertDialog


fun Activity.showSuccessDialog(
    title:String,
    message:String,
    onButtonClickClickListener: OnButtonClickClickListener
){
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok"){_,_->
            onButtonClickClickListener.onButtonClick()
        }
        .show()


}fun Activity.showErrorDialog(
    title:String,
    message:String,
    onButtonClickClickListener: OnButtonClickClickListener
){
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok"){dialog,_->
            dialog.dismiss()
        }
        .show()


}
fun interface OnButtonClickClickListener{
    fun onButtonClick()
}