package com.jimmy.pokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.pokeapp.R
import kotlinx.android.synthetic.main.activity_card.*

class card : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        addFavorites.setOnClickListener{
            val intent = Intent(this, Favorites::class.java)
            startActivity(intent)
        }

    }
}