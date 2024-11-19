package com.example.mypokedexcompose.framework.database.berries

import com.example.mypokedexcompose.data.dataSource.local.berries.BerryEntity
import com.example.mypokedexcompose.data.dataSource.local.berries.BerryRoomDataSource

class BerryRoomDataSource(
    private val berryDao: BerryDao
) : BerryRoomDataSource {
    override val berries = berryDao.fetchBerries()
    override fun getBerryByName(name: String) = berryDao.fetchBerryByName(name)
    override suspend fun isEmpty() = berryDao.countBerries() == 0
    override suspend fun saveBerries(berryEntity: List<BerryEntity>) =
        berryDao.saveBerry(berryEntity)
}