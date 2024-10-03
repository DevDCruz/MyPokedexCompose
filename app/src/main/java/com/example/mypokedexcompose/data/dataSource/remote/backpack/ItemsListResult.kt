package com.example.mypokedexcompose.data.dataSource.remote.backpack

class ItemsListResult (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ItemResult>
)