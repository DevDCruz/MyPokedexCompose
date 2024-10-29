package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.mappers.BerryMapper
import com.example.mypokedexcompose.domaindatalayer.berries.Berry
import com.example.mypokedexcompose.framework.database.berries.BerryRoomDataSource
import com.example.mypokedexcompose.framework.remote.berries.BerryServerDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BerryRepository(
    private val berryServerDataSource: BerryServerDataSource,
    private val berryRoomDataSource: BerryRoomDataSource,
    private val berryMapper: BerryMapper
) {

    val berries: Flow<List<Berry>> =
        berryRoomDataSource.berries.map { berryMapper.toDomainList(it) }

    suspend fun fetchBerries() {
        if (berryRoomDataSource.isEmpty()) {
            val remoteBerries = berryServerDataSource.fetchBerries()
            val localBerries = berryMapper.fromRemoteToEntityList(remoteBerries)
            berryRoomDataSource.saveBerries(localBerries)
        }
    }

    suspend fun fetchBerryByName(name: String): Flow<Berry?> = flow {
        val localBerry = berryRoomDataSource.getBerryByName(name).firstOrNull()
        if (localBerry != null && localBerry.isDetailFetched) {
            Log.d("BerryRepository", "fetchBerryByName in local: ${localBerry.name}")
            emit(berryMapper.toDomain(localBerry))
        } else {
            val remoteBerry = berryServerDataSource.fetchBerryByName(name)
            val newLocalBerry = berryMapper.fromRemoteToEntity(remoteBerry)
            Log.d("BerryRepository", "fetchBerryByName in remote: ${newLocalBerry.name}")
            newLocalBerry.isDetailFetched = true
            berryRoomDataSource.saveBerries(listOf(newLocalBerry))
            emit(berryMapper.toDomain(newLocalBerry))
        }
    }
}

