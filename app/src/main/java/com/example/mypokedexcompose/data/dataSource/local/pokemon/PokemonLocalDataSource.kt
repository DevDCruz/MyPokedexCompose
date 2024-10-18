package com.example.mypokedexcompose.data.dataSource.local.pokemon

class PokemonLocalDataSource(
    private val pokemonDao: PokemonDao
) {

    fun getPokemonByName(name: String) = pokemonDao.getPokemonByName(name)

    fun getPokemonById(id: Int) = pokemonDao.getPokemonById(id)

    suspend fun savePokemon(pokemonEntity: PokemonEntity) =
        pokemonDao.savePokemon(listOf(pokemonEntity))

}