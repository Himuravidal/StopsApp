package com.example.stopsapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [StopData::class, RouteData::class],
    version = 1,
    exportSchema = false
)
abstract class StopDataBase : RoomDatabase() {

    abstract fun getStopDao(): StopDao
    abstract fun getRouteDao(): RouteDao

    companion object {
        @Volatile
        private var INSTANCE: StopDataBase? = null

        fun getDataBase(context: Context): StopDataBase {
            return INSTANCE ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    StopDataBase::class.java,
                    "stopDb"
                )
                    .build()
                INSTANCE = tempInstance
                return tempInstance
            }
        }
    }
}