package com.example.mypokedexcompose.data.items

import com.example.mypokedexcompose.data.berries.BerryResult

class ItemsListResult (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ItemResult>
)