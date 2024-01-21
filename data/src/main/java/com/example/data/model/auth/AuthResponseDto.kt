package com.example.data.model.auth

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.auth.AuthResponse
import com.google.gson.annotations.SerializedName

@Parcelize
 data class AuthResponseDto(
	@field:SerializedName("statusMsg")
	val statusMsg:String? = null,
	@field:SerializedName("message")
	val message:String? = null,
	@field:SerializedName("user")
	val userDto: UserDto? = null,
	@field:SerializedName("token")
	val token: String? = null

) : Parcelable{
	fun toAuthResponse():AuthResponse{
		return AuthResponse(
			statusMsg = statusMsg,
			message = message,
			user = userDto?.toUser(),
			token = token
		)
	}
}