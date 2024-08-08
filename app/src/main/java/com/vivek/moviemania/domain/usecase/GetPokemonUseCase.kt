package com.vivek.pokidex.domain.usecase

import androidx.paging.PagingData
import com.vivek.pokidex.domain.model.Pokemon
import com.vivek.pokidex.domain.repository.PokemonRepository
import com.vivek.pokidex.presentation.ui.components.SortOrder
import com.vivek.pokidex.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case for getting a flow of PagingData of Pokemon.
 */
class GetPokemonUseCase @Inject constructor(val repository: PokemonRepository) {
    fun execute(): Flow<Resource<List<Pokemon>>> = repository.getPokemon()


    fun execute(
        sortOrder: SortOrder = SortOrder.None,
        filter: String? = null
    ): Flow<Resource<List<Pokemon>>> {
        return repository.getPokemon().map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val filteredData = filterData(resource.data ?: emptyList(), filter)
                    val sortedData = sortData(filteredData, sortOrder)
                    Resource.Success(sortedData)
                }
                is Resource.Loading -> {
                    Resource.Loading(resource.data)
                }
                is Resource.Error -> {
                    Resource.Error(throwable = Throwable("Error fetching data"), data = resource.data)
                }
            }
        }
    }

    private fun filterData(data: List<Pokemon>, filter: String?): List<Pokemon> {
        return if (filter.isNullOrEmpty()) {
            data
        } else {
            data.filter { it.name.contains(filter, ignoreCase = true) }
        }
    }

    private fun sortData(data: List<Pokemon>, sortOrder: SortOrder): List<Pokemon> {
        return when (sortOrder) {
            SortOrder.Level -> data.sortedByDescending { it.level?.toIntOrNull() }
            SortOrder.Hp -> data.sortedByDescending { it.hp?.toIntOrNull() }
            SortOrder.None -> data
        }
    }
}
