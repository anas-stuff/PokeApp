package com.jimmy.pokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimmy.pokeapp.adapter.FavoritesAdapter
import com.jimmy.pokeapp.adapter.MainAdapter
import com.jimmy.pokeapp.db.PokeDao
import com.jimmy.pokeapp.db.PokeFavoritesDatabase
import kotlinx.android.synthetic.main.activity_favorites.*
import kotlinx.android.synthetic.main.activity_favorites.recyclerView
import kotlinx.android.synthetic.main.activity_favorites.view.*
import kotlinx.android.synthetic.main.activity_main.*

class Favorites : AppCompatActivity() {
    private var pokeDao : PokeDao?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        //
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        pokeDao = PokeFavoritesDatabase.getInstance(this).getPokeFavoriteDao()

        if(pokeDao?.getFavorites().toString() != "[]")
            textView.visibility = View.GONE
        val intent = Intent(this, MainActivity::class.java)
        home.setOnClickListener{
            startActivity(intent)
        }
    }
    override fun onResume(){
        super.onResume()
        recyclerView.adapter = FavoritesAdapter(pokeDao?.getFavorites() as ArrayList<Pokemon>)
    }
}

















