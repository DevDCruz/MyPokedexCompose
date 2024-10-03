package com.example.mypokedexcompose.data.dataSource.local.database.berries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokedexcompose.data.berries.Firmness
import com.example.mypokedexcompose.data.berries.Flavor

@Entity
data class BerryEntity (
    @PrimaryKey val id: Int?,
    val firmness: Firmness?,
    val flavors: List<Flavor>?,
    val name: String?,
    val size: Int?,
    val smoothness: Int?,
    var favorite: Boolean,
    var isDetailFetched: Boolean = false
)