package com.vivek.moviemania.data.remote

import com.vivek.moviemania.data.remote.models.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET(Endpoints.CARDS)
    suspend fun getPokemonCards(@Query("pageSize") pageSize: Int=50,
                                @Query("page") page: Int): PokemonResponse
}
