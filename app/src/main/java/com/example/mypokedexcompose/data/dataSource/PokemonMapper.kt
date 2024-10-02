package com.example.mypokedexcompose.data.dataSource

import com.example.mypokedexcompose.data.dataSource.database.pokemon.PokemonEntity
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult
import com.example.mypokedexcompose.data.pokemon.Pokemon

class PokemonMapper {
    fun toDomain(pokemonEntity: PokemonEntity): Pokemon {
        return Pokemon(
            id = pokemonEntity.id,
            name = pokemonEntity.name,
            height = pokemonEntity.height ?: 9999999,
            weight = pokemonEntity.weight ?: 9999999,
            types = pokemonEntity.types ?: emptyList(),
            favorite = pokemonEntity.favorite
        )
    }

    fun toEntity(pokemon: Pokemon): PokemonEntity {
        return PokemonEntity(
            id = pokemon.id,
            name = pokemon.name,
            height = pokemon.height,
            weight = pokemon.weight,
            types = pokemon.types,
            favorite = pokemon.favorite
        )
    }

    fun fromRemoteToEntity(pokemonResult: PokemonResult): PokemonEntity {
        return PokemonEntity(
            id = pokemonResult.id,
            name = pokemonResult.name,
            height = pokemonResult.height,
            weight = pokemonResult.weight,
            types = pokemonResult.types,
            favorite = false,
            isDetailFetched = false
        )

    }

    fun fromRemoteToEntityList(pokemons: List<PokemonResult>): List<PokemonEntity> {
        return pokemons.mapIndexed { index, pokemonResult ->
            PokemonEntity(
                id = index + 1,
                name = pokemonResult.name,
                height = pokemonResult.height,
                weight = pokemonResult.weight,
                types = pokemonResult.types,
                favorite = false,
                isDetailFetched = false
            )
        }
    }

    fun toEntityList(pokemons: List<Pokemon>): List<PokemonEntity> {
        return pokemons.map { toEntity(it) }
    }

    fun toDomainList(pokemons: List<PokemonEntity>): List<Pokemon> {
        return pokemons.map { toDomain(it) }
    }
}