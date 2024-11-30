package com.example.mypokedexcompose.framework.database.berries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokedexcompose.domain.berries.Firmness
import com.example.mypokedexcompose.domain.berries.Flavor

@Entity
data class BerryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firmness: Firmness?,
    val flavors: List<Flavor>?,
    val name: String?,
    val size: Int?,
    val smoothness: Int?,
    var favorite: Boolean,
    var detailFetched: Boolean = false
)