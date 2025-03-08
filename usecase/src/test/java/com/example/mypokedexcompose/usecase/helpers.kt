package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain

fun sampleBackPackItemDomain(id: Int) = BackpackItemDomain(
    id = id,
    attributes = null,
    category = null,
    cost = 10,
    name = "test",
    sprites = null,
    favorite = false,
    detailFetched = false
)

fun sampleBackPackItemDomainList(vararg ids : Int) = ids.map{ sampleBackPackItemDomain(it) }

fun samplePokemonDomain(id: Int) = PokemonDomain(
    id = id,
    name = "test",
    height = 10,
    weight = 10,
    types = null,
    favorite = false,
    detailFetched = false
)

fun samplePokemonDomainList(vararg ids : Int) = ids.map{ samplePokemonDomain(it) }