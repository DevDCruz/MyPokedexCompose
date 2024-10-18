package com.example.mypokedexcompose.data.region

import com.example.mypokedexcompose.framework.GeocoderRegionDataSource

class RegionRepository(private val geocoderRegionDataSource: GeocoderRegionDataSource) {

    suspend fun findLastRegion(): String = geocoderRegionDataSource.findLastRegion()

}