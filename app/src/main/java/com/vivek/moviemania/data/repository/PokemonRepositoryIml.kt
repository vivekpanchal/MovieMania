package com.vivek.moviemania.data.repository

import com.vivek.moviemania.data.local.dao.PokemonDao
import com.vivek.moviemania.data.remote.ApiService
import com.vivek.moviemania.data.remote.models.toDomainModel
import com.vivek.moviemania.data.remote.models.toEntity
import com.vivek.moviemania.domain.model.Pokemon
import com.vivek.moviemania.domain.repository.PokemonRepository
import com.vivek.moviemania.utils.Resource
import com.vivek.moviemania.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryIml @Inject constructor(
    private val apiService: ApiService,
    private val dao: PokemonDao
) : PokemonRepository {

    private var currentPage = 1

    override fun getPokemon(): Flow<Resource<List<Pokemon>>> {
        currentPage=1 //reset page
        return networkBoundResource(
            query = { dao.getAllPokemon().map { it.map { entity -> entity.toDomainModel() } } },
            fetch = { apiService.getPokemonCards(page = currentPage) },
            saveFetchResult = { pokemonResponse ->
                dao.insertAll(pokemonResponse.data.map { it.toEntity(currentPage) })
            },
            shouldFetch = { it.isEmpty() }
        )
    }

    override suspend fun getPokemonDetail(pokemonId: String): Pokemon? {
        return dao.getPokemonById(pokemonId)?.toDomainModel()
    }

    override  fun loadMorePokemon(): Flow<Resource<List<Pokemon>>> {
        currentPage++ //increment page
        return networkBoundResource(
            query = { dao.getAllPokemon().map { it.map { entity -> entity.toDomainModel() } } },
            fetch = { apiService.getPokemonCards(page = currentPage) },
            saveFetchResult = { pokemonResponse ->
                dao.insertAll(pokemonResponse.data.map { it.toEntity(currentPage) })
            },
            shouldFetch = { true }
        )
    }


}
