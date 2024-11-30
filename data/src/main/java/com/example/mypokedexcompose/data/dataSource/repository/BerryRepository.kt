package com.example.mypokedexcompose.data.dataSource.repository


import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.berries.BerryLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class BerryRepository(
    private val berryRoomDataSource: BerryLocalDataSource,
    private val berryServerDataSource: BerryRemoteDataSource
) : IBerryRepository {

    override val berries: Flow<List<BerryDomain>> =
        berryRoomDataSource.berries

    override suspend fun fetchBerries() {
        if (berryRoomDataSource.isEmpty()) {
            val remoteBerries = berryServerDataSource.fetchBerries()
            berryRoomDataSource.saveBerries(remoteBerries)
        }
    }

    override suspend fun fetchBerryByName(name: String): Flow<BerryDomain?> = flow {
        val localBerry = berryRoomDataSource.getBerryByName(name).firstOrNull()
        if (localBerry != null && localBerry.detailFetched) {
            Log.d("BerryRepository", "Berry ${localBerry.name} fetched from local")
            emit(localBerry)
        } else {
            val remoteBerry = berryServerDataSource.fetchBerryByName(name)
            Log.d("BerryRepository", "Berry ${remoteBerry.name} fetched from Server")
            remoteBerry.detailFetched = true
            berryRoomDataSource.saveBerries(listOf(remoteBerry))
            emit(remoteBerry)
        }
    }
}

