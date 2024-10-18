package com.example.mypokedexcompose.data.dataSource

import android.location.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String

    suspend fun Location.toRegion(): String
}

