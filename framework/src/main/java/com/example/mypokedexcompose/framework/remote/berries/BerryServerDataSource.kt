package com.example.mypokedexcompose.framework.remote.berries

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.framework.mappers.BerryMapper

class BerryServerDataSource(
    private val berryClient: BerryClient,
    private val berryMapper: BerryMapper
) : BerryRemoteDataSource {
    override suspend fun fetchBerries(): List<BerryDomain> {
        val response = berryClient.instance.fetchBerries(64)
        Log.d("BerryServerDataSource", "fetchBerries: ${response.results.size}")
        return response.results.map { berries -> berryMapper.fromRemoteToDomain(berries) }
    }

    override suspend fun fetchBerryByName(name: String): BerryDomain = berryClient.instance
        .getBerryByName(name).let { berryMapper.fromRemoteToDomain(it) }
}
