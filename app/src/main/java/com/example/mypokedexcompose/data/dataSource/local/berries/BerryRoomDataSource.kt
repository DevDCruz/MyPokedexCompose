package com.example.mypokedexcompose.data.dataSource.local.berries

import kotlinx.coroutines.flow.Flow

interface BerryLocalDataSource {
    val berries: Flow<List<BerryEntity>>
    fun getBerryByName(name: String): Flow<BerryEntity?>

    suspend fun isEmpty(): Boolean

    suspend fun saveBerries(berryEntity: List<BerryEntity>)
}

class BerryRoomDataSource(
    private val berryDao: BerryDao
) : BerryLocalDataSource {
    override val berries = berryDao.fetchBerries()
    override fun getBerryByName(name: String) = berryDao.fetchBerryByName(name)
    override suspend fun isEmpty() = berryDao.countBerries() == 0
    override suspend fun saveBerries(berryEntity: List<BerryEntity>) =
        berryDao.saveBerry(berryEntity)
}