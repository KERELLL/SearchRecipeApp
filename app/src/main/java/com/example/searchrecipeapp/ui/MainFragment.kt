package com.example.searchrecipeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchrecipeapp.R
import com.example.searchrecipeapp.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var viewModel: SearchRecipesViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val args : MainFragmentArgs by navArgs()
    private val app_id = "2e6f7367"
    private val app_key = "4bc3e809af557f18224968346c015038"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[SearchRecipesViewModel::class.java]
        val calories = "" + args.min + "-" + args.max

        binding.progressBar.isVisible = false
        viewModel.getRecipesSearch(args.ingredients, app_key, app_id, calories)

        recyclerViewAdapter = RecyclerViewAdapter(requireContext()){
            val action = MainFragmentDirections.actionMainFragmentToRecipeDetailsFragment(it,
            args.ingredients,
            args.min,
            args.max)
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = recyclerViewAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.createProjectButton.setOnClickListener {
            val ingredients = binding.editTextSearch.text.toString()
            viewModel.getRecipesSearch(args.ingredients, app_key, app_id, calories)
        }
        subscribeSearchRecipes()
    }

    private fun subscribeSearchRecipes(){
        viewModel.publicLoginStateFlow.onEach {
            when(it){
                is SearchRecipesViewModel.SearchRecipesUIState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is SearchRecipesViewModel.SearchRecipesUIState.Success -> {
                    binding.progressBar.isVisible = false
                    recyclerViewAdapter.recipesList.clear()
                    recyclerViewAdapter.recipesList = it.recipeList
                    recyclerViewAdapter.notifyDataSetChanged()
                }
                is SearchRecipesViewModel.SearchRecipesUIState.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), it.e, Toast.LENGTH_LONG).show()
                }
            }
        }.launchIn(lifecycleScope)
    }
}