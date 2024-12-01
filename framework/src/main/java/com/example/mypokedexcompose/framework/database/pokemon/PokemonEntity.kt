package com.example.mypokedexcompose.framework.database.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokedexcompose.domain.pokemon.Type

@Entity
data class PokemonEntity(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val height: Int?,
    val weight: Int?,
    val types: List<Type>?,
    var favorite: Boolean,
    var detailFetched: Boolean = false
)
