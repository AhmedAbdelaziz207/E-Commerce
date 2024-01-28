package com.example.e_commerce.ui

import android.app.Activity
import android.app.AlertDialog
import androidx.fragment.app.Fragment


fun Activity.showSuccessDialog(
    title:String,
    message:String,
    onButtonClickClickListener: OnButtonClickClickListener
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok") { _, _ ->
            onButtonClickClickListener.onButtonClick()
        }
        .show()
}
fun Fragment.showDialog(
    title:String,
    message:String,
    onButtonClickClickListener: OnButtonClickClickListener
){
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok"){dialog,_->
            dialog.dismiss()
        }
        .show()


}

    fun Activity.showErrorDialog(
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