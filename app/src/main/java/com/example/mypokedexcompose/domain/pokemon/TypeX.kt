package com.example.mypokedexcompose.domain.pokemon


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypeX(
    @PrimaryKey(autoGenerate = true)
    val name: String,
    val url: String
)