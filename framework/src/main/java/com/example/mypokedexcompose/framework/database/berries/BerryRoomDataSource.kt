package com.example.mypokedexcompose.framework.database.berries

import com.example.mypokedexcompose.data.dataSource.local.berries.BerryLocalDataSource
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.framework.mappers.BerryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class BerryRoomDataSource(
    private val berryDao: BerryDao,
    private val berryMapper: BerryMapper
) : BerryLocalDataSource {

    override val berries =
        berryDao.fetchBerries().map { berries -> berryMapper.fromEntityListToDomainList(berries) }

    override fun getBerryByName(name: String): Flow<BerryDomain?> {
        return berryDao.fetchBerryByName(name)
            .map { berry -> berry?.let { berryMapper.fromEntityToDomain(berry) } }
    }

    override suspend fun isEmpty() = berryDao.countBerries() == 0

    override suspend fun saveBerries(berryDomain: List<BerryDomain>) {
        val berries = berryMapper.fromDomainListToEntityList(berryDomain)
        berryDao.saveBerry(berries)
    }
}