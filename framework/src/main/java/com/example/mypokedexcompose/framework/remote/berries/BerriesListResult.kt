package com.example.mypokedexcompose.framework.remote.berries

data class BerriesListResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BerryResult>
)