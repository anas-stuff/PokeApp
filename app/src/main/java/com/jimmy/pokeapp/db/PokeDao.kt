package com.jimmy.pokeapp.db

import androidx.room.*
import com.jimmy.pokeapp.Pokemon

@Dao
interface PokeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorites(poke :Pokemon)

    @Query("SELECT * From Pokemon")
    fun getFavorites() :List<Pokemon>

    @Update
    fun updateFavorites(poke :Pokemon)

    @Delete
    fun deleteFavorites(poke :Pokemon)
}














