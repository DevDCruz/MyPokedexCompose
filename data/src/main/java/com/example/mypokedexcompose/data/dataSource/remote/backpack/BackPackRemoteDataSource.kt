package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain

interface BackPackRemoteDataSource {
    suspend fun fetchItems(): List<BackpackItemDomain>

    suspend fun fetchItemByName(name: String): BackpackItemDomain
}
