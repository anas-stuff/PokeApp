package com.jimmy.pokeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.pokeapp.Pokemon
import com.jimmy.pokeapp.R
import com.jimmy.pokeapp.db.PokeDao
import com.jimmy.pokeapp.db.PokeFavoritesDatabase


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_card.view.*

class MainAdapter(var pokemons: List<Pokemon>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {




    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val poke_name = itemView.pokeName as TextView
        val poke_img = itemView.pokeImage as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val x = LayoutInflater.from(parent.context).inflate(R.layout.activity_card, parent, false)
        return ViewHolder(x)
    }
    //private lateinit var pokeDao: PokeDao? =null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemons: Pokemon = pokemons[position]
        holder.poke_name.text = pokemons.name
        Picasso.get()
            .load(pokemons.imageUrl)
            .into(holder.poke_img)
        holder.itemView.addFavorites.setOnClickListener{
            PokeFavoritesDatabase.getInstance(holder.itemView.context).getPokeFavoriteDao().addFavorites(pokemons)
            
        }

    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

}

