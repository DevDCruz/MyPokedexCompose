package com.example.mypokedexcompose.data.dataSource.remote.berry

import com.example.mypokedexcompose.data.berries.BerryResult

class BerryRemoteDataSource {
    suspend fun fetchBerries(): List<BerryResult> = BerryClient.instance
        .fetchBerries(64)
        .results

    suspend fun fetchBerryByName(name: String): BerryResult = BerryClient.instance
        .getBerryByName(name)

}
