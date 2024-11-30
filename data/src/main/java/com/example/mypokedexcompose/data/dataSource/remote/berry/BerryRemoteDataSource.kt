package com.example.mypokedexcompose.data.dataSource.remote.berry

import com.example.mypokedexcompose.domain.berries.BerryDomain

interface BerryRemoteDataSource {
    suspend fun fetchBerries(): List<BerryDomain>

    suspend fun fetchBerryByName(name: String): BerryDomain
}
