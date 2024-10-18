package com.example.mypokedexcompose.data.region

import com.example.mypokedexcompose.data.dataSource.GeocoderRegionDataSource

class RegionRepository(private val geocoderRegionDataSource: GeocoderRegionDataSource) {

    suspend fun findLastRegion(): String = geocoderRegionDataSource.findLastRegion()

}