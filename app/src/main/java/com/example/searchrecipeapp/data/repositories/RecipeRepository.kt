package com.example.searchrecipeapp.data.repositories

import com.example.searchrecipeapp.data.OperationResult
import com.example.searchrecipeapp.data.network.ApiProvider
import com.example.searchrecipeapp.data.network.responses.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RecipeRepository {
    private val apiService = ApiProvider.apiService

    suspend fun getRecipesSearch(ingredients: String, appKey : String, appId : String, calories : String) : OperationResult<RecipeResponse, String?>{
        return withContext(Dispatchers.IO){
            try {
                val result = apiService.getRecipesSearch(ingredients, appKey, appId, calories)
                return@withContext OperationResult.Success(result)
            }catch (e: Throwable){
                return@withContext OperationResult.Error(e.message)
            }
        }
    }
}