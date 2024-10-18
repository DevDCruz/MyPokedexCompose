package com.example.mypokedexcompose.data.dataSource

import android.location.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}
