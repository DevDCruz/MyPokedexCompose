package com.example.mypokedexcompose.data.dataSource.remote.pokemon

class PokemonRemoteDataSource {
    suspend fun fetchPokemon(name: String): PokemonResult = PokemonClient.instance
        .getPokemonByName(name)

    suspend fun fetchRandomPokemon(id: Int): PokemonResult = PokemonClient.instance
        .getPokemonById(id)
}
