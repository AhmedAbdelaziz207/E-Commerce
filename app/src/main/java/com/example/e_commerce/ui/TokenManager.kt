package com.example.e_commerce.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE
    )

   fun saveToken(token:String?){
      val editor = sharedPreferences.edit()
      editor.putString(Constants.TOKEN,token).apply()
   }
   fun getToken():String?{
      return sharedPreferences.getString(Constants.TOKEN,null)
   }
}