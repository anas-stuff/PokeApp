package com.jimmy.pokeapp.db

import com.jimmy.pokeapp.Pokemon

class PokeRebo(private val pokeDao: PokeDao) {

    suspend fun deleteFavorites(poke: Pokemon){
        pokeDao.deleteFavorites(poke)
    }
}