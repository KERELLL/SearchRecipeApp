package com.example.searchrecipeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchrecipeapp.data.OperationResult
import com.example.searchrecipeapp.data.repositories.RecipeRepository
import com.example.searchrecipeapp.ui.models.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchRecipesViewModel : ViewModel() {
    private val recipeRepository = RecipeRepository()

    private val recipeStateFlow : MutableStateFlow<SearchRecipesUIState> =
        MutableStateFlow(SearchRecipesUIState.Loading)
    val publicLoginStateFlow = recipeStateFlow.asStateFlow()

    fun getRecipesSearch(ingredients: String, appKey : String, appId : String, calories: String){
        viewModelScope.launch {
            val result = recipeRepository.getRecipesSearch(ingredients, appKey, appId, calories)
            recipeStateFlow.value = when(result){
                is OperationResult.Success ->{
                    val recipeList = mutableListOf<Recipe>()
                    for (i in result.data.hits){
                        recipeList.add(
                            Recipe(
                                name = i.recipe.label,
                                image = i.recipe.image,
                                ingredients = i.recipe.ingredientLines,
                                calories = i.recipe.calories))
                    }
                    SearchRecipesUIState.Success(recipeList)
                }
                is OperationResult.Error -> SearchRecipesUIState.Error(result.data)
            }
        }
    }

    sealed class SearchRecipesUIState(){
        object Loading : SearchRecipesUIState()
        class Error(val e: String?) : SearchRecipesUIState()
        class Success(val recipeList: MutableList<Recipe>) : SearchRecipesUIState()
    }
}