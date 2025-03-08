package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain

fun sampleBackPackItemDomain(name: String) = BackpackItemDomain(
    id = 1,
    attributes = null,
    category = null,
    cost = 10,
    name = name,
    sprites = null,
    favorite = false,
    detailFetched = false
)

fun sampleBackPackItemDomainList(vararg name: String) = name.map { sampleBackPackItemDomain(it) }

fun samplePokemonDomain(name: String) = PokemonDomain(
    id = 1,
    name = name,
    height = 10,
    weight = 10,
    types = null,
    favorite = false,
    detailFetched = false
)

fun samplePokemonDomainList(vararg name: String) = name.map { samplePokemonDomain(it) }

fun sampleBerryDomain(name: String) = BerryDomain(
    firmness = null,
    flavors = null,
    id = 1,
    name = name,
    size = 10,
    smoothness = 10,
    favorite = false,
    detailFetched = false
)

fun sampleBerryDomainList(vararg name: String) = name.map { sampleBerryDomain(it) }