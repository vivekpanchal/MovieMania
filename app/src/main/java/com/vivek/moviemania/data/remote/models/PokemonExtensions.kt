package com.vivek.pokidex.data.remote.models

import com.vivek.pokidex.data.local.entity.PokemonEntity
import com.vivek.pokidex.domain.model.Pokemon


fun PokemonDTO.toEntity(page: Int): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        imageUrl = images.small,
        types = types?.joinToString(", ") ?: "Unknown",
        subtypes = subtypes?.joinToString(", "),
        level = level?: "Unknown",
        hp = hp?.toIntOrNull(),
        attacks = attacks,
        weaknesses = weaknesses,
        abilities = abilities,
        resistances = resistances,
        page = page // Assign the page number to the entity
    )
}


fun PokemonEntity.toDomainModel(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        images = ImagesDTO(imageUrl, imageUrl), // Use the same URL for both small and large for simplicity
        types = types.split(", ").filter { it.isNotBlank() },
        subtypes = subtypes?.split(", "),
        level = level,
        hp = hp?.toString(),
        attacks = attacks,
        weaknesses = weaknesses,
        abilities = abilities,
        resistances = resistances
    )
}

