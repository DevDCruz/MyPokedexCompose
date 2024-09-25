package com.example.mypokedexcompose.data.dataSource

import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.example.mypokedexcompose.ui.common.Constants.DEFAULT_REGION
import com.example.mypokedexcompose.ui.common.getFromLocationCompat

class RegionDataSource(app: Application, private val locationDataSource: LocationDataSource) {
    private val geocoder = Geocoder(app)

    suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}