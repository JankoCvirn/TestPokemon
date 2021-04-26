package com.jankocvirn.testpokemon.repository

import com.jankocvirn.testpokemon.client.APIClient
import com.jankocvirn.testpokemon.model.pokemon.Pokemon
import com.jankocvirn.testpokemon.model.response.PokemonResponse

class ApiRepository {

    private val apiServices by lazy {
        APIClient.apiService()
    }

    suspend fun getPokemon(randomId: Int): Pokemon? {
        val result = apiServices.requestPokemon(randomId)
        return if (result.body() != null) {
            mapPokemon(result.body() as PokemonResponse)
        } else {
            null
        }
    }
}
