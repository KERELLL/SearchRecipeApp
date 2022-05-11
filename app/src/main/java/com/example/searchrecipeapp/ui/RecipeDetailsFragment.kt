package com.example.searchrecipeapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.searchrecipeapp.R
import com.example.searchrecipeapp.databinding.FragmentRecipeDetailsBinding
import com.example.searchrecipeapp.ui.models.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding : FragmentRecipeDetailsBinding
    private lateinit var database : DatabaseReference
    private val mAuth = FirebaseAuth.getInstance()
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
        database = FirebaseDatabase.getInstance().getReference("Favourites")
//        database.child(mAuth.currentUser!!.uid).setValue(Recipe(
//            args.recipeDetails.name,
//            args.recipeDetails.image,
//            args.recipeDetails.ingredients,
//            args.recipeDetails.calories
//        )).addOnSuccessListener {
//            Toast.makeText(requireContext(), "Suscc", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener {
//            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//        }
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