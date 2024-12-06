package com.example.mypokedexcompose.data.region

import com.example.mypokedexcompose.data.dataSource.RegionDataSource
import javax.inject.Inject


class RegionRepository @Inject constructor(private val geocoderRegionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = geocoderRegionDataSource.findLastRegion()

}