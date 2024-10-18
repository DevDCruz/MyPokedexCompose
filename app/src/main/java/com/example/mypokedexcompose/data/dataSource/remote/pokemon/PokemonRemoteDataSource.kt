package com.example.mypokedexcompose.data.dataSource.remote.pokemon

class PokemonRemoteDataSource(
    private val pokemonClient: PokemonClient
) {
    suspend fun fetchPokemon(name: String): PokemonResult = pokemonClient.instance
        .getPokemonByName(name)

    suspend fun fetchRandomPokemon(id: Int): PokemonResult = pokemonClient.instance
        .getPokemonById(id)
}
