package com.example.mypokedexcompose.framework.database.backpack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BackPackDao {
    @Query("SELECT * FROM ItemEntity")
    fun fetchItems(): Flow<List<ItemEntity>>

    @Query("SELECT * FROM ItemEntity WHERE name = :name")
    fun fetchItemByName(name: String): Flow<ItemEntity?>

    @Query("SELECT COUNT(*) FROM ItemEntity")
    suspend fun countItems(): Int

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun saveItem(itemEntity: List<ItemEntity>)
}