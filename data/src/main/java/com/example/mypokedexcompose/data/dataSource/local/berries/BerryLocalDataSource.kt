package com.example.mypokedexcompose.data.dataSource.local.berries

import com.example.mypokedexcompose.domain.berries.BerryDomain
import kotlinx.coroutines.flow.Flow

interface BerryLocalDataSource {

    val berries: Flow<List<BerryDomain>>

    fun getBerryByName(name: String): Flow<BerryDomain?>

    suspend fun isEmpty(): Boolean

    suspend fun saveBerries(berryDomain: List<BerryDomain>)
}

