package com.example.mypokedexcompose.framework

import android.location.Geocoder
import com.example.mypokedexcompose.data.dataSource.LocationDataSource
import com.example.mypokedexcompose.data.dataSource.RegionDataSource
import com.example.mypokedexcompose.domain.Location

class GeocoderRegionDataSource(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) :
    RegionDataSource {
    private val defaultRegion = "US"

    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: defaultRegion

    override suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: defaultRegion
    }
}