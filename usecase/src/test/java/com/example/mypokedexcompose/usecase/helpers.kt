package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain

fun samplePokemonName(name : String) = PokemonDomain(
    id = 10,
    name = name,
    height = 10,
    weight = 10,
    types = null,
    favorite = false,
    detailFetched = false
)

fun samplePokemons(vararg ids: String) = ids.map { samplePokemonName(it) }

fun sampleBackPackItem(name: String) = BackpackItemDomain(
    name = name,
    favorite = false,
    detailFetched = false,
    id = 10,
    sprites = null,
    cost = 10,
    category = null,
    attributes = null
)

fun sampleBackPackItems(vararg names: String) = names.map { sampleBackPackItem(it) }

fun sampleBerry(name : String) = BerryDomain(
    name = name,
    flavors = null,
    firmness = null,
    size = 10,
    smoothness = 10,
    id = 10,
    favorite = false,
    detailFetched = false
)

fun sampleBerries(vararg names: String) = names.map { sampleBerry(it) }