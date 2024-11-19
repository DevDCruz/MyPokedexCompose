package com.example.mypokedexcompose.data.region

import com.example.mypokedexcompose.data.dataSource.RegionDataSource


class RegionRepository(private val geocoderRegionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = geocoderRegionDataSource.findLastRegion()

}