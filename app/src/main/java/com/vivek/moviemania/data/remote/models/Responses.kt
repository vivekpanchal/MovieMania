package com.vivek.pokidex.data.remote.models

import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "data")
    val `data`: List<PokemonDTO>,
    @Json(name = "page")
    val page: Int,
    @Json(name = "pageSize")
    val pageSize: Int,
    @Json(name = "totalCount")
    val totalCount: Int
)


@JsonClass(generateAdapter = true)
data class PokemonDTO(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "images")
    val images: ImagesDTO,
    @Json(name = "types")
    val types: List<String>?,
    @Json(name = "subtypes")
    val subtypes: List<String>?,
    @Json(name = "level")
    val level: String?,
    @Json(name = "hp")
    val hp: String?,
    @Json(name = "attacks")
    val attacks: List<Attack>?,
    @Json(name = "weaknesses")
    val weaknesses: List<Weakness>?,
    @Json(name = "abilities")
    val abilities: List<Ability>?,
    @Json(name = "resistances")
    val resistances: List<Resistance>?
)

@JsonClass(generateAdapter = true)
data class Attack(
    @Json(name = "name")
    val name: String,
    @Json(name = "damage")
    val damage: String,
    @Json(name = "text")
    val text: String
)

@JsonClass(generateAdapter = true)
data class Weakness(
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: String
)

@JsonClass(generateAdapter = true)
data class Ability(
    @Json(name = "name")
    val name: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "type")
    val type: String
)

@JsonClass(generateAdapter = true)
data class Resistance(
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: String
)

@JsonClass(generateAdapter = true)
data class ImagesDTO(
    @Json(name = "small")
    val small: String,
    @Json(name = "large")
    val large: String
)
