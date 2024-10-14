package com.example.mypokedexcompose.data.pokemon


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Entity
@Serializable
data class TypeX(
    @PrimaryKey(autoGenerate = true)
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)