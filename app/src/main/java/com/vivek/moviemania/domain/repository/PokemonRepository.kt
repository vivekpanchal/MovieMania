package com.vivek.pokidex.domain.repository

import androidx.paging.PagingData
import com.vivek.pokidex.domain.model.Pokemon
import com.vivek.pokidex.utils.Resource
import kotlinx.coroutines.flow.Flow


interface PokemonRepository {
    fun getPokemon(): Flow<Resource<List<Pokemon>>>
    suspend fun getPokemonDetail(pokemonId: String): Pokemon?
    fun loadMorePokemon():Flow<Resource<List<Pokemon>>>
}
