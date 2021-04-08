package com.jimmy.pokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jimmy.pokeapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.activity_main.*
import com.jimmy.pokeapp.adapter.MainAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModels<PokemonVM>().value.pokemon.observe(this, {
            val adapter = MainAdapter(it as ArrayList<Pokemon>)
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
            progressBar.visibility = View.GONE
        })

        var intent = Intent(this, Favorites::class.java)


        favorites.setOnClickListener{
            startActivity(intent)
        }


    }

}

@Entity
data class Pokemon(
    @PrimaryKey
    val name: String,
    val url: String) {

    private val idm get() = url.dropLast(1).split("/").last()
    val imageUrl get() = "https://pokeres.bastionbot.org/images/pokemon/$idm.png"
}

data class Response(val results: List<Pokemon>)

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemons(): Response
}

class PokemonRepo {
    suspend fun getPokemons() = ServiceProvider.getAPI().getPokemons().results
}

class PokemonVM : ViewModel() {
    val pokemon = liveData { emit(PokemonRepo().getPokemons()) }
}

object ServiceProvider {
    fun getAPI(): ApiService = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}


