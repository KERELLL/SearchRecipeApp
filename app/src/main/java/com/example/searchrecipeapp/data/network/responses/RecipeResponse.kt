package com.example.searchrecipeapp.data.network.responses

data class RecipeResponse(
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val more: Boolean,
    val q: String,
    val to: Int
)