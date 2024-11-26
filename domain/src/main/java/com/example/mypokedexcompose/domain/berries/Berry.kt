package com.example.mypokedexcompose.domain.berries

data class Berry(
    val firmness: Firmness?,
    val flavors: List<Flavor>?,
    val id: Int?,
    val name: String?,
    val size: Int?,
    val smoothness: Int?,
    var favorite: Boolean = false
)