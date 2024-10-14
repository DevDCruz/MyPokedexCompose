package com.example.mypokedexcompose.data.region

import com.example.mypokedexcompose.data.dataSource.RegionDataSource

class RegionRepository(private val regionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}