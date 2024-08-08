package com.vivek.moviemania.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vivek.moviemania.data.remote.models.Ability
import com.vivek.moviemania.data.remote.models.Attack
import com.vivek.moviemania.data.remote.models.Resistance
import com.vivek.moviemania.data.remote.models.Weakness

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val types: String,
    val subtypes: String?,
    val level: String?,
    val hp: Int?,
    val attacks: List<Attack>?,
    val weaknesses: List<Weakness>?,
    val abilities: List<Ability>?,
    val resistances: List<Resistance>?,
    val page: Int // to help with paging
)
