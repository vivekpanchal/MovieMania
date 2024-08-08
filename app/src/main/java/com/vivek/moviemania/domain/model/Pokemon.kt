package com.vivek.moviemania.domain.model

import com.vivek.moviemania.data.remote.models.Ability
import com.vivek.moviemania.data.remote.models.Attack
import com.vivek.moviemania.data.remote.models.ImagesDTO
import com.vivek.moviemania.data.remote.models.Resistance
import com.vivek.moviemania.data.remote.models.Weakness

data class Pokemon(
    val id: String,
    val name: String,
    val images: ImagesDTO,
    val types: List<String>,
    val subtypes: List<String>?,
    val level: String?,
    val hp: String?,
    val attacks: List<Attack>?,
    val weaknesses: List<Weakness>?,
    val abilities: List<Ability>?,
    val resistances: List<Resistance>?
)
