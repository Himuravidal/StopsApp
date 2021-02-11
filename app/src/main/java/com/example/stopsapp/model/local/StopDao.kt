package com.example.stopsapp.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStop(listStop: List<StopData>)

    @Query("SELECT * FROM stop_table")
     fun getAllStopData(): Flow<List<StopData>>
}