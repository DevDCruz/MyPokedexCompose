package com.example.mypokedexcompose.data.dataSource.remote.berry

class BerryRemoteDataSource {
    suspend fun fetchBerries(): List<BerryResult> = BerryClient.instance
        .fetchBerries(64)
        .results

    suspend fun fetchBerryByName(name: String): BerryResult = BerryClient.instance
        .getBerryByName(name)

}
