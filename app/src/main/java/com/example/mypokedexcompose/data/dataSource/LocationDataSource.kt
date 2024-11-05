package com.example.mypokedexcompose.data.dataSource

import com.example.mypokedexcompose.domain.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}
