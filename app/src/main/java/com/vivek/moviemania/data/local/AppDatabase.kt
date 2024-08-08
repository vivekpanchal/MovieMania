package com.vivek.moviemania.data.local

import com.vivek.moviemania.data.local.converters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vivek.moviemania.data.local.dao.PokemonDao
import com.vivek.moviemania.data.local.entity.PokemonEntity


@Database(entities = [PokemonEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
