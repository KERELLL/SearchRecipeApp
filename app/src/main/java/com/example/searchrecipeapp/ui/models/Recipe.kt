package com.example.searchrecipeapp.ui.models

data class Recipe(
    val name : String,
    val image : String,
    val ingredients : List<String>,
    val calories : Double
)