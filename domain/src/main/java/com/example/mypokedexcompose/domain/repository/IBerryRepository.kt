package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.berries.BerryDomain

interface IBerryRepository {
    val berries: kotlinx.coroutines.flow.Flow<List<BerryDomain>>

    suspend fun fetchBerries()

    suspend fun fetchBerryByName(name: String): kotlinx.coroutines.flow.Flow<BerryDomain?>
}