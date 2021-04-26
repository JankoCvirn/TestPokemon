package com.jankocvirn.testpokemon.model.pokemon

data class Pokemon(
    val name: String,
    val frontImage: String,
    val backImage: String,
    val stats: List<Stat?>?,
    val moves: List<Move?>?,
)

data class Stat(
    val baseStat: Int?,
    val effort: Int?,
    val name: String?,
    val url: String?
)

data class Move(
    val name: String?,
    val url: String?
)
