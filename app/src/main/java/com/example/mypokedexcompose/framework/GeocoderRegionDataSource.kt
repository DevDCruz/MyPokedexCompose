package com.example.mypokedexcompose.framework

import android.location.Geocoder
import com.example.mypokedexcompose.data.dataSource.LocationDataSource
import com.example.mypokedexcompose.data.dataSource.RegionDataSource
import com.example.mypokedexcompose.domain.Location
import com.example.mypokedexcompose.ui.common.Constants.DEFAULT_REGION
import com.example.mypokedexcompose.ui.common.getFromLocationCompat

class GeocoderRegionDataSource(
    private val geocoder: Geocoder,
    private val locationDataSource: com.example.mypokedexcompose.data.dataSource.LocationDataSource
) :
    com.example.mypokedexcompose.data.dataSource.RegionDataSource {

    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    override suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}