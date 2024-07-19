package com.example.e_commerce.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.domain.model.auth.User
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

    fun saveUser(user: User){
        val editor = sharedPreferences.edit()
        editor.putString(Constants.USER_NAME,user.name).apply()
        editor.putString(Constants.E_MAIL,user.email).apply()
        editor.putString(Constants.PHONE_NUMBER,user.phone).apply()
    }
    fun getUser():User{
        val userName = sharedPreferences.getString(Constants.USER_NAME,null)
        val email = sharedPreferences.getString(Constants.E_MAIL,null)
        val phoneNumber = sharedPreferences.getString(Constants.PHONE_NUMBER,null)

        return User(name = userName, email = email, phone = phoneNumber)
    }

    fun saveState(state : Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean("state", state).apply()
    }
    fun getState():Boolean{
        return sharedPreferences.getBoolean("state", false)
    }
}