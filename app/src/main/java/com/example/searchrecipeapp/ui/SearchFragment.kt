package com.example.searchrecipeapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.searchrecipeapp.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchButton.setOnClickListener {
            val ingredients = binding.productsEditText.text.toString()
            val max = binding.caloriesMaxEditText.text.toString().toInt()
            val min = binding.caloriesMinEditText.text.toString().toInt()
            val action = SearchFragmentDirections.actionSearchFragmentToMainFragment(
                ingredients,
                min,
                max
            )
            findNavController().navigate(action)
        }
    }
}