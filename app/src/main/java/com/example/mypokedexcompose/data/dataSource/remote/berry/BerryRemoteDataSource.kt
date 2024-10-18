package com.example.mypokedexcompose.data.dataSource.remote.berry

class BerryRemoteDataSource(
    private val berryClient: BerryClient
) {
    suspend fun fetchBerries(): List<BerryResult> = berryClient.instance
        .fetchBerries(64)
        .results

    suspend fun fetchBerryByName(name: String): BerryResult = berryClient.instance
        .getBerryByName(name)

}
