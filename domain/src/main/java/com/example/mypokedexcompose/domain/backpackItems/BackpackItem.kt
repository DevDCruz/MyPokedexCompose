package com.example.mypokedexcompose.domain.backpackItems


data class BackpackItem(
    val id: Int,
    val attributes: List<Attribute>?,
    val category: Category?,
    val cost: Int?,
    val name: String?,
    val sprites: Sprites?,
    val favorite: Boolean = false,
    var detailFetched: Boolean = false
)
