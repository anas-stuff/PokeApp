package com.jimmy.pokeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.pokeapp.Pokemon
import com.jimmy.pokeapp.R
import com.jimmy.pokeapp.db.PokeFavoritesDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_card.view.*
import kotlinx.android.synthetic.main.activity_favorites.view.*

class FavoritesAdapter (var pokemons: List<Pokemon>): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val poke_name = itemView.pokeName as TextView
        val poke_img = itemView.pokeImage as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val x = LayoutInflater.from(parent.context).inflate(R.layout.activity_card, parent, false)
        return ViewHolder(x)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemons: Pokemon = pokemons[position]
        holder.poke_name.text = pokemons.name
        Picasso.get()
            .load(pokemons.imageUrl)
            .into(holder.poke_img)
        holder.itemView.addFavorites.setOnClickListener{
            println(pokemons.name)
            PokeFavoritesDatabase.getInstance(holder.itemView.context).getPokeFavoriteDao().deleteFavorites(pokemons)
            holder.itemView.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return pokemons.size
    }


}

