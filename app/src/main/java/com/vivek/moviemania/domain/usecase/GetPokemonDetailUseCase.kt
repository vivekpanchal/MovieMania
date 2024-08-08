package com.vivek.pokidex.domain.usecase

import com.vivek.pokidex.domain.model.Pokemon
import com.vivek.pokidex.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * Use case for getting a specific Pokemon by its ID.
 */
class GetPokemonDetailUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(pokemonId: String): Pokemon? {
        return repository.getPokemonDetail(pokemonId)
    }
}
