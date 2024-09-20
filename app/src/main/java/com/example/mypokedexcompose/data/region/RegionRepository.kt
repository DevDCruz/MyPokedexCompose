package com.example.mypokedexcompose.data.region

import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.example.mypokedexcompose.ui.common.Constants.DEFAULT_REGION
import com.example.mypokedexcompose.ui.common.getFromLocationCompat
import com.example.mypokedexcompose.ui.common.lastLocation
import com.google.android.gms.location.LocationServices

class RegionRepository(app: Application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)
    private val geocoder = Geocoder(app)

    suspend fun findLastRegion(): String = fusedLocationClient.lastLocation()?.toRegion() ?: DEFAULT_REGION

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}