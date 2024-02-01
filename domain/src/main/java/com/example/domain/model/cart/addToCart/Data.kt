package com.example.domain.model.cart.addToCart

data class Data(
	val cartOwner: String? = null,
	val createdAt: String? = null,
	val totalCartPrice: Int? = null,
	val v: Int? = null,
	val id: String? = null,
	val products: List<CartProduct?>? = null,
	val updatedAt: String? = null
)
