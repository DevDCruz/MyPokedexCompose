package com.example.mypokedexcompose.data.dataSource.local.berries

import kotlinx.coroutines.flow.Flow

interface BerryRoomDataSource {

    val berries: Flow<List<BerryEntity>>

    fun getBerryByName(name: String): Flow<BerryEntity?>

    suspend fun isEmpty(): Boolean

    suspend fun saveBerries(berryEntity: List<BerryEntity>)
}

