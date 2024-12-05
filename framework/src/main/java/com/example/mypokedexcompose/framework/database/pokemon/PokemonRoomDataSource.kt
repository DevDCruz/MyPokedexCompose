package com.example.mypokedexcompose.framework.database.pokemon

import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonLocalDataSource
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.framework.mappers.PokemonMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class PokemonRoomDataSource(
    private val pokemonDao: PokemonDao,
    private val pokemonMapper: PokemonMapper
) : PokemonLocalDataSource {

    override fun getPokemonByName(name: String): Flow<PokemonDomain?> {
        return pokemonDao.getPokemonByName(name).map { pokemon ->
            pokemon?.let { pokemonMapper.fromEntityToDomain(pokemon) }
        }
    }

    override fun getPokemonById(id: Int): Flow<PokemonDomain?> {
        return pokemonDao.getPokemonById(id).map { pokemon ->
            pokemon?.let { pokemonMapper.fromEntityToDomain(pokemon) }
        }
    }

    override suspend fun savePokemon(pokemonDomain: PokemonDomain) {
        val pokemon = pokemonMapper.fromDomainToEntity(pokemonDomain)
        pokemonDao.savePokemon(listOf(pokemon))
    }

}