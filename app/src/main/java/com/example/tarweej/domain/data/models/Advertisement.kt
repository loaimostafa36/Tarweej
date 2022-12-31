package com.example.tarweej.domain.data.models

data class Advertisement(
    val id: String?,
    val storeId: String?,
    val title: String?,
    var price: Double?,
    var offerPrice: Double?,
    var images: List<String>?,
    var isFavorite: Boolean?,
    var createdAt: Any?,
    var store: Store?,
)