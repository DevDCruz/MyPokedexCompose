package com.example.mypokedexcompose.data.dataSource

import com.example.mypokedexcompose.domain.Location


interface RegionDataSource {
    suspend fun findLastRegion(): String

    suspend fun Location.toRegion(): String
}

