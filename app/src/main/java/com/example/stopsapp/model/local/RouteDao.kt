package com.example.stopsapp.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RouteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoute(listRoute: RouteData)

    @Query("SELECT * FROM route_table")
    fun getAllStopData(): Flow<List<RouteData>>

}
