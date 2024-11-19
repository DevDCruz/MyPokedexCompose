package com.example.mypokedexcompose.framework.remote.berries

import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryResult
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryServerDataSource

class BerryServerDataSource(
    private val berryClient: BerryClient
) : BerryServerDataSource {
    override suspend fun fetchBerries(): List<BerryResult> = berryClient.instance
        .fetchBerries(64)
        .results

    override suspend fun fetchBerryByName(name: String): BerryResult = berryClient.instance
        .getBerryByName(name)
}
