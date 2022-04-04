package com.example.searchrecipeapp.data.network

import com.example.searchrecipeapp.data.network.responses.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getRecipesSearch(
        @Query("q") ingredients: String,
        @Query("app_key") appKey : String,
        @Query("app_id") appId : String,
        @Query("calories") calories: String
    ) : RecipeResponse
}