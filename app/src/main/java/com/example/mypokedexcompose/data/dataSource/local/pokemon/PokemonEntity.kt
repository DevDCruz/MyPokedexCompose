package com.example.mypokedexcompose.data.dataSource.local.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokedexcompose.domaindatalayer.pokemon.Type

@Entity
data class PokemonEntity(

    @PrimaryKey val id: Int,
    val name: String,
    val height: Int?,
    val weight: Int?,
    val types: List<Type>?,
    var favorite: Boolean,
    var isDetailFetched: Boolean = false
)
