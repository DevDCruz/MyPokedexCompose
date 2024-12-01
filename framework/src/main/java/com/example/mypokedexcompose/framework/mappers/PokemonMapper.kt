package com.example.mypokedexcompose.framework.mappers

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.framework.database.pokemon.PokemonEntity
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonResult

class PokemonMapper {
    fun fromEntityToDomain(pokemonEntity: PokemonEntity): PokemonDomain {
        return PokemonDomain(
            id = pokemonEntity.id,
            name = pokemonEntity.name,
            height = pokemonEntity.height ?: 9999999,
            weight = pokemonEntity.weight ?: 9999999,
            types = pokemonEntity.types ?: emptyList(),
            favorite = pokemonEntity.favorite,
            detailFetched = pokemonEntity.detailFetched
        )
    }

    fun fromDomainToEntity(pokemonDomain: PokemonDomain): PokemonEntity {
        return PokemonEntity(
            id = pokemonDomain.id,
            name = pokemonDomain.name,
            height = pokemonDomain.height,
            weight = pokemonDomain.weight,
            types = pokemonDomain.types,
            favorite = pokemonDomain.favorite,
            detailFetched = pokemonDomain.detailFetched
        )
    }

    fun fromRemoteToDomain(pokemonResult: PokemonResult): PokemonDomain {
        return PokemonDomain(
            id = pokemonResult.id,
            name = pokemonResult.name,
            height = pokemonResult.height,
            weight = pokemonResult.weight,
            types = pokemonResult.types,
            favorite = false,
            detailFetched = false
        )
    }

    fun fromEntityListToDomainList(pokemons: List<PokemonEntity>): List<PokemonDomain> {
        return pokemons.map { fromEntityToDomain(it) }
    }
}