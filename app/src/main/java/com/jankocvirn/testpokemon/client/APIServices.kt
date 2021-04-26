package com.jankocvirn.testpokemon.client

import com.jankocvirn.testpokemon.model.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIServices {

    @Headers("Accept: application/json")
    @GET("/api/v2/pokemon/{id}")
    suspend fun requestPokemon(
        @Path("id") id: Int
    ): Response<PokemonResponse>
}
