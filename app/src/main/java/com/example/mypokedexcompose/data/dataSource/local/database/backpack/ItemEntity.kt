package com.example.mypokedexcompose.data.dataSource.local.database.backpack

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokedexcompose.data.items.Attribute
import com.example.mypokedexcompose.data.items.Category
import com.example.mypokedexcompose.data.items.Sprites

@Entity
data class ItemEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int?,
    val attributes: List<Attribute>?,
    val category: Category?,
    val cost: Int?,
    val name: String?,
    val sprites: Sprites?,
    val favorite: Boolean = false,
    var isDetailFetched: Boolean = false
)
