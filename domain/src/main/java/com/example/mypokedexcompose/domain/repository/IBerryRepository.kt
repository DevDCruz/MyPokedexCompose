package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.berries.Berry

interface IBerryRepository {
    val berries: kotlinx.coroutines.flow.Flow<List<Berry>>

    suspend fun fetchBerries()

    suspend fun fetchBerryByName(name: String): kotlinx.coroutines.flow.Flow<Berry?>
}