package com.example.mypokedexcompose.data.dataSource.local

import com.example.mypokedexcompose.data.dataSource.database.berries.BerryDao
import com.example.mypokedexcompose.data.dataSource.database.berries.BerryEntity

class BerryLocalDataSource(
    private val berryDao: BerryDao
) {
    val berries = berryDao.fetchBerries()
    fun getBerryByName(name: String) = berryDao.fetchBerryByName(name)
    suspend fun isEmpty() = berryDao.countBerries() == 0
    suspend fun saveBerries(berryEntity: List<BerryEntity>) = berryDao.saveBerry(berryEntity)

}