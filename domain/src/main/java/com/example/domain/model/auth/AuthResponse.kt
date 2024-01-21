package com.example.domain.model.auth


 data class AuthResponse(
	val statusMsg:String? = null,
	val message:String? = null,
	val user: User? = null,
	val token: String? = null

)