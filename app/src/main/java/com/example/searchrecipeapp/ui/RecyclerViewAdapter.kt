package com.example.searchrecipeapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchrecipeapp.R
import com.example.searchrecipeapp.ui.models.Recipe
import kotlin.math.roundToInt
import android.view.ViewGroup as ViewGroup

class RecyclerViewAdapter(private val context : Context, val click: (Recipe) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    var recipesList: MutableList<Recipe> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listview, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(recipesList[position].image).into(holder.recipeImageView)
        holder.recipeNameTextView.text = recipesList[position].name
        holder.caloriesTextView.text = recipesList[position].calories.roundToInt().toString() + " kcal"
        holder.itemView.setOnClickListener {
            click.invoke(recipesList[position])
        }
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recipeImageView: ImageView = itemView.findViewById(R.id.recipeImageView)
        val recipeNameTextView: TextView = itemView.findViewById(R.id.recipeNameTextView)
        val caloriesTextView : TextView = itemView.findViewById(R.id.caloriesItemTextView)
    }
}