package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.domain.berries.Berry
import com.example.mypokedexcompose.data.dataSource.local.berries.BerryLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.BerryMapper
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BerryRepository(
    private val berryRemoteDataSource: BerryRemoteDataSource,
    private val berryLocalDataSource: BerryLocalDataSource,
    private val berryMapper: BerryMapper
) {

    val berries: Flow<List<Berry>> =
        berryLocalDataSource.berries.map { berryMapper.toDomainList(it) }

    suspend fun fetchBerries() {
        if (berryLocalDataSource.isEmpty()) {
            val remoteBerries = berryRemoteDataSource.fetchBerries()
            val localBerries = berryMapper.fromRemoteToEntityList(remoteBerries)
            berryLocalDataSource.saveBerries(localBerries)
        }
    }

    suspend fun fetchBerryByName(name: String): Flow<Berry?> = flow {
        val localBerry = berryLocalDataSource.getBerryByName(name).firstOrNull()
        if (localBerry != null && localBerry.isDetailFetched) {
            Log.d("BerryRepository", "fetchBerryByName in local: ${localBerry.name}")
            emit(berryMapper.toDomain(localBerry))
        } else {
            val remoteBerry = berryRemoteDataSource.fetchBerryByName(name)
            val newLocalBerry = berryMapper.fromRemoteToEntity(remoteBerry)
            Log.d("BerryRepository", "fetchBerryByName in remote: ${newLocalBerry.name}")
            newLocalBerry.isDetailFetched = true
            berryLocalDataSource.saveBerries(listOf(newLocalBerry))
            emit(berryMapper.toDomain(newLocalBerry))
        }
    }
}

