package com.example.mypokedexcompose.framework.database.pokedex

import com.example.mypokedexcompose.data.dataSource.local.pokedex.PokedexLocalDataSource
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.framework.mappers.PokemonMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PokedexRoomDataSource @Inject constructor(
    private val pokedexDao: PokedexDao,
    private val pokemonMapper: PokemonMapper
) : PokedexLocalDataSource {

    override val pokemons = pokedexDao.fetchPokedex()
        .map { pokemons -> pokemonMapper.fromEntityListToDomainList(pokemons) }

    override suspend fun savePokemons(pokemonEntity: List<PokemonDomain>) {
        val pokemons = pokemonEntity.map { pokemon -> pokemonMapper.fromDomainToEntity(pokemon) }
        pokedexDao.savePokemons(pokemons)
    }

    override suspend fun countPokemons(): Int {
        val count = pokedexDao.countPokemons()
        return count
    }

    override suspend fun getPokedexForRegion(offset: Int, limit: Int): List<PokemonDomain> {
        val pokemons = pokedexDao.fetchRegionalPokedex(offset, limit)
        return pokemons.map { pokemon -> pokemonMapper.fromEntityToDomain(pokemon) }
    }
}
