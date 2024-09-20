package com.example.mypokedexcompose.data.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Entity
@Serializable
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    @SerialName("name")
    val name: String,
    @SerialName("id")
    val id: Int,
    @SerialName("height")
    val height: Int,
    @SerialName("weight")
    val weight: Int,
    @SerialName("types")
    val types: List<Type>?
)

