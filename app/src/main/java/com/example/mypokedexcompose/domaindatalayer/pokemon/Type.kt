package com.example.mypokedexcompose.domaindatalayer.pokemon


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Type(
    @PrimaryKey(autoGenerate = true)
    val slot: Int,
    val type: TypeX
)