package com.example.mypokedexcompose.framework.region

import android.location.Geocoder
import com.example.mypokedexcompose.data.dataSource.LocationDataSource
import com.example.mypokedexcompose.data.dataSource.RegionDataSource
import com.example.mypokedexcompose.domain.Location
import javax.inject.Inject


class GeocoderRegionDataSource @Inject constructor(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) :
    RegionDataSource {
    private val DEFAULT_REGION = "US"

    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    override suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}