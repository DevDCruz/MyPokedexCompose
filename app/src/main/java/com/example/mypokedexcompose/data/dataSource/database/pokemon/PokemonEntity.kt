package com.example.mypokedexcompose.data.dataSource.database.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokedexcompose.data.pokemon.Type
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class PokemonEntity(

    @SerialName("id")
    @PrimaryKey val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("height")
    val height: Int?,
    @SerialName("weight")
    val weight: Int?,
    @SerialName("types")
    val types: List<Type>?,
    @SerialName("favorite")
    var favorite: Boolean,
    var isDetailFetched: Boolean = false
)
