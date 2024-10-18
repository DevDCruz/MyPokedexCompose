package com.example.mypokedexcompose.framework

import android.annotation.SuppressLint
import android.location.Location
import com.example.mypokedexcompose.data.dataSource.LocationDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlayServicesLocationDataSource(private val fusedLocationClient: FusedLocationProviderClient) :
    LocationDataSource {

    override suspend fun findLastLocation(): Location? = fusedLocationClient.lastLocation()
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}