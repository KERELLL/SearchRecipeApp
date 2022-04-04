package com.example.searchrecipeapp.data.network.responses

data class Ingredient(
    val food: String,
    val foodCategory: String,
    val foodId: String,
    val image: String,
    val measure: String,
    val quantity: Double,
    val text: String,
    val weight: Double
)