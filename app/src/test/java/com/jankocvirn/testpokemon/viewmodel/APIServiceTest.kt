package com.jankocvirn.testpokemon.viewmodel

import com.jankocvirn.testpokemon.client.APIServices
import com.jankocvirn.testpokemon.model.response.PokemonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class APIServiceTest {

    @Mock
    lateinit var apiServices: APIServices
    @Mock
    lateinit var pokemonResult: PokemonResponse

    @Test
    fun `pokemon work correct`() = runBlocking {
        val id = (1..100).random()
        val response = Response.success(pokemonResult)
        BDDMockito.given(apiServices.requestPokemon(id)).willReturn(response)
        val result = apiServices.requestPokemon(id)
        assertEquals(result, response)
    }
    @Test
    fun `pokemon expect valid data`() = runBlocking {
        val id = (1..100).random()
        val response = Response.success(pokemonResult)
        BDDMockito.given(apiServices.requestPokemon(id)).willReturn(response)
        val result = apiServices.requestPokemon(id)
        Assert.assertNotNull(result)
        Assert.assertNotNull(id)
    }
    @Test
    fun `ApiServices expect methodCall`():Unit = runBlocking {
        val id = (1..100).random()
        apiServices.requestPokemon(id)
        Mockito.verify(apiServices).requestPokemon(id)
    }
}
