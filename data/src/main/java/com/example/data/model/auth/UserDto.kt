package com.example.data.model.auth

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.auth.User
import com.google.gson.annotations.SerializedName

@Parcelize
data class UserDto(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable{
	fun toUser():User{
		return User(
			name = this.name,
			email = this.email,
			role = this.role
		)
	}
}