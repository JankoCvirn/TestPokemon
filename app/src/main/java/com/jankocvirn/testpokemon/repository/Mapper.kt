package com.jankocvirn.testpokemon.repository

import com.jankocvirn.testpokemon.model.pokemon.Move
import com.jankocvirn.testpokemon.model.pokemon.Pokemon
import com.jankocvirn.testpokemon.model.pokemon.Stat
import com.jankocvirn.testpokemon.model.response.PokemonResponse

fun mapPokemon(response: PokemonResponse): Pokemon {
    return Pokemon(
        name = response.name ?: "",
        frontImage = response.sprites?.frontDefault ?: "",
        backImage = response.sprites?.backDefault ?: "",
        moves = mapMoves(response.moves),
        stats = mapStats(response.stats)
    )
}

private fun mapMoves(moves: List<PokemonResponse.Move?>?): List<Move> {
    val list = mutableListOf<Move>()
    moves?.forEach {
        list.add(
            Move(
                name = it?.move?.name,
                url = it?.move?.url
            )
        )
    }
    return list.toMutableList()
}

private fun mapStats(stats: List<PokemonResponse.Stat?>?): List<Stat> {
    val list = mutableListOf<Stat>()
    stats?.forEach {
        list.add(
            Stat(
                baseStat = it?.baseStat,
                effort = it?.effort,
                name = it?.stat?.name,
                url = it?.stat?.url
            )
        )
    }
    return list.toList()
}
