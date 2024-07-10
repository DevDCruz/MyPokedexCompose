package com.example.mypokedexcompose.data.berries

data class BerriesResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BerryResult>
)