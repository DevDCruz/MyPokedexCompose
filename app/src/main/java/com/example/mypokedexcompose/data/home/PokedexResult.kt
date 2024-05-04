package com.example.mypokedexcompose.data.home


import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.data.detail.pokemonresult.PokemonResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexResult(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<PokemonResult>
)

val pokemons = emptyList<Pokemon>(
    /*Pokemon(
         name = "bulbasaur",
         url = "https://pokeapi.co/api/v2/pokemon/1/",
         id = 1,
         height = 7,
         weight = 69,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
         ,
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")))
     ),
     Pokemon(
         name = "ivysaur",
         url = "https://pokeapi.co/api/v2/pokemon/2/",
         id = 2,
         height = 10,
         weight = 130,
         sprites =  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")))
     ),
     Pokemon(
         name = "venusaur",
         url = "https://pokeapi.co/api/v2/pokemon/3/",
         id = 3,
         height = 20,
         weight = 1000,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")))
     ),
     Pokemon(
         name = "charmander",
         url = "https://pokeapi.co/api/v2/pokemon/4/",
         id = 4,
         height = 6,
         weight = 85,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "fire", url = "https://pokeapi.co/api/v2/type/10/")))
     ),
     Pokemon(
         name = "charmeleon",
         url = "https://pokeapi.co/api/v2/pokemon/5/",
         id = 5,
         height = 11,
         weight = 190,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "fire", url = "https://pokeapi.co/api/v2/type/10/")))
     ),
     Pokemon(
         name = "charizard",
         url = "https://pokeapi.co/api/v2/pokemon/6/",
         id = 6,
         height = 17,
         weight = 905,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "fire", url = "https://pokeapi.co/api/v2/type/10/")))
     ),
     Pokemon(
         name = "squirtle",
         url = "https://pokeapi.co/api/v2/pokemon/7/",
         id = 7,
         height = 5,
         weight = 90,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "water", url = "https://pokeapi.co/api/v2/type/11/")))
     ),
     Pokemon(
         name = "wartortle",
         url = "https://pokeapi.co/api/v2/pokemon/8/",
         id = 8,
         height = 10,
         weight = 225,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "water", url = "https://pokeapi.co/api/v2/type/11/")))
     ),
     Pokemon(
         name = "blastoise",
         url = "https://pokeapi.co/api/v2/pokemon/9/",
         id = 9,
         height = 16,
         weight = 855,
         sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
         spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png",
         types = listOf(Type(slot = 1, type = TypeX(name = "water", url = "https://pokeapi.co/api/v2/type/11/")))
     )*/
)



