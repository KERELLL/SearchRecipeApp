package com.example.searchrecipeapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.searchrecipeapp.R
import com.example.searchrecipeapp.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding : FragmentRecipeDetailsBinding
    private val args : RecipeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(requireContext()).load(args.recipeDetails.image).into(binding.recipeImageView)
        binding.recipeNameTextView.text = args.recipeDetails.name
        for (i in args.recipeDetails.ingredients){
            binding.ingredientsTextView.text = binding.ingredientsTextView.text.toString() + i + "\n"
        }

        binding.backButton.setOnClickListener {
            val action = RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToMainFragment(
                args.ingredients,
                args.min,
                args.max
            )
            findNavController().navigate(action)
        }
    }

}