package com.example.mypokedexcompose.data.items


data class Item(
    val attributes: List<Attribute>?,
    val category: Category?,
    val cost: Int?,
    val id: Int?,
    val name: String?,
    val sprites: Sprites?,
    val favorite: Boolean = false
)
