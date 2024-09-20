package com.example.mypokedexcompose.data.dataSource

import com.example.mypokedexcompose.data.berries.Berry
import com.example.mypokedexcompose.data.berries.BerryClient
import com.example.mypokedexcompose.data.berries.BerryResult

class BerryRemoteDataSource {
    suspend fun fetchBerries(): List<Berry> = BerryClient
        .instance
        .fetchBerries(64)
        .results
        .map {
            it.todomainModel()
        }

    suspend fun fetchBerryByName(name: String): Berry = BerryClient
        .instance
        .getBerryByName(name)

}

private fun BerryResult.todomainModel(): Berry =
    Berry(
        firmness = firmness,
        flavors = flavors,
        growthTime = growthTime,
        id = id,
        item = item,
        maxHarvest = maxHarvest,
        name = name,
        naturalGiftPower = naturalGiftPower,
        naturalGiftType = naturalGiftType,
        size = size,
        smoothness = smoothness,
        soilDryness = soilDryness
    )