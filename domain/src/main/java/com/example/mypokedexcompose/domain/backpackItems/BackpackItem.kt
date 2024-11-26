package com.example.mypokedexcompose.domain.backpackItems


data class BackpackItem(
    val attributes: List<Attribute>?,
    val category: Category?,
    val cost: Int?,
    val id: Int?,
    val name: String?,
    val sprites: Sprites?,
    val favorite: Boolean = false
)