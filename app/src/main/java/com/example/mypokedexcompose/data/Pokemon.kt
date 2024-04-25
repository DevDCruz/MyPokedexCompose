package com.example.mypokedexcompose.data

data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
    val bitImage: String
)

val pokemons = (1..151).map {
    Pokemon(
        it,
        "Pokemon $it",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
    )
}
