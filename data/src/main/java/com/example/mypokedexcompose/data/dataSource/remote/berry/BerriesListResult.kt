package com.example.mypokedexcompose.data.dataSource.remote.berry

data class BerriesListResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BerryResult>
)