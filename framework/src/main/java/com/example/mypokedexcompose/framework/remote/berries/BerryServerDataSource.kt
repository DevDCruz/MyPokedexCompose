package com.example.mypokedexcompose.framework.remote.berries

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.framework.mappers.BerryMapper
import javax.inject.Inject

internal class BerryServerDataSource @Inject constructor(
    private val berryService: BerryService,
    private val berryMapper: BerryMapper
) : BerryRemoteDataSource {
    override suspend fun fetchBerries(): List<BerryDomain> {
        val response = berryService.fetchBerries(64)
        Log.d("BerryServerDataSource", "fetchBerries: ${response.results.size}")
        return response.results.map { berries -> berryMapper.fromRemoteToDomain(berries) }
    }

    override suspend fun fetchBerryByName(name: String): BerryDomain = berryService
        .getBerryByName(name).let { berryMapper.fromRemoteToDomain(it) }
}
