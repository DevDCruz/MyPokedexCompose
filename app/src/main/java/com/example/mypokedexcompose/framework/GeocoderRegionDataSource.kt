package com.example.mypokedexcompose.framework

import android.location.Geocoder
import android.location.Location
import com.example.mypokedexcompose.data.dataSource.RegionDataSource
import com.example.mypokedexcompose.ui.common.Constants.DEFAULT_REGION
import com.example.mypokedexcompose.ui.common.getFromLocationCompat

class GeocoderRegionDataSource(
    private val geocoder: Geocoder,
    private val playServicesLocationDataSource: PlayServicesLocationDataSource
) :
    RegionDataSource {

    override suspend fun findLastRegion(): String =
        playServicesLocationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    override suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}