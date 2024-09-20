package com.example.mypokedexcompose.data.berries

import com.example.mypokedexcompose.data.dataSource.BerryRemoteDataSource

class BerryRepository(private val berryRemoteDataSource: BerryRemoteDataSource) {

    suspend fun fetchBerries(): List<Berry> = berryRemoteDataSource.fetchBerries()

    suspend fun fetchBerryByName(name: String): Berry = berryRemoteDataSource.fetchBerryByName(name)
}

