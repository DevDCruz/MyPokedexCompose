package com.example.mypokedexcompose.data.dataSource.remote.berry

interface BerryRemoteDataSource {
    suspend fun fetchBerries(): List<BerryResult>

    suspend fun fetchBerryByName(name: String): BerryResult
}
