package com.example.mypokedexcompose.data.dataSource.repository


import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.berries.BerryLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.BerryMapper
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import com.example.mypokedexcompose.domain.berries.Berry
import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BerryRepository(
    private val berryRoomDataSource: BerryLocalDataSource,
    private val berryServerDataSource: BerryRemoteDataSource,
    private val berryMapper: BerryMapper
) : IBerryRepository {

    override val berries: Flow<List<Berry>> =
        berryRoomDataSource.berries.map { berryMapper.toDomainList(it) }

    override suspend fun fetchBerries() {
        if (berryRoomDataSource.isEmpty()) {
            val remoteBerries = berryServerDataSource.fetchBerries()
            val localBerries = berryMapper.fromRemoteToEntityList(remoteBerries)
            berryRoomDataSource.saveBerries(localBerries)
        }
    }

    override suspend fun fetchBerryByName(name: String): Flow<Berry?> = flow {
        val localBerry = berryRoomDataSource.getBerryByName(name).firstOrNull()
        if (localBerry != null && localBerry.isDetailFetched) {
            Log.d("BerryRepository", "Berry ${localBerry.name} fetched from local")
            emit(berryMapper.toDomain(localBerry))
        } else {
            val remoteBerry = berryServerDataSource.fetchBerryByName(name)
            Log.d("BerryRepository", "Berry ${remoteBerry.name} fetched from Server")
            val newLocalBerry = berryMapper.fromRemoteToEntity(remoteBerry)
            newLocalBerry.isDetailFetched = true
            berryRoomDataSource.saveBerries(listOf(newLocalBerry))
            emit(berryMapper.toDomain(newLocalBerry))
        }
    }
}

