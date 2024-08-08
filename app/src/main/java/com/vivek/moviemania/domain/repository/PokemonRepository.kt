package com.vivek.moviemania.domain.repository

import com.vivek.moviemania.domain.model.Pokemon
import com.vivek.moviemania.utils.Resource
import kotlinx.coroutines.flow.Flow


interface PokemonRepository {
    fun getPokemon(): Flow<Resource<List<Pokemon>>>
    suspend fun getPokemonDetail(pokemonId: String): Pokemon?
    fun loadMorePokemon():Flow<Resource<List<Pokemon>>>
}
