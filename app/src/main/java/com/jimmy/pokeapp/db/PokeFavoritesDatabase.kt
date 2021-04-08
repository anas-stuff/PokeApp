package com.jimmy.pokeapp.db

import android.content.Context
import android.os.AsyncTask
import android.provider.SyncStateContract.Helpers.update
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jimmy.pokeapp.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokeFavoritesDatabase : RoomDatabase() {
    abstract fun getPokeFavoriteDao() :PokeDao
    companion object {
        fun getInstance(context: Context)=
            Room.databaseBuilder(context.applicationContext, PokeFavoritesDatabase::class.java,"favorites_pokemon")
                .allowMainThreadQueries().build()
    }

}