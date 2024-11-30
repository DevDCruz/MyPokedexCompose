package com.example.mypokedexcompose.framework.database.berries

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BerryDao {

    @Query("SELECT * FROM BerryEntity")
    fun fetchBerries(): Flow<List<BerryEntity>>

    @Query("SELECT * FROM BerryEntity WHERE name = :name")
    fun fetchBerryByName(name: String): Flow<BerryEntity?>

    @Query("SELECT * FROM BerryEntity WHERE id = :id")
    suspend fun fetchBerryById(id: Int): BerryEntity?

    @Query("SELECT COUNT(*) FROM BerryEntity")
    suspend fun countBerries(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBerry(berryEntity: List<BerryEntity>)
}