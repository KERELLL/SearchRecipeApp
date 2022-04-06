package com.example.searchrecipeapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val name : String,
    val image : String,
    val ingredients : List<String>,
    val calories : Double
) : Parcelable