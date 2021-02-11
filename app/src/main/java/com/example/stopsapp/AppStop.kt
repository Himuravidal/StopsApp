package com.example.stopsapp

import android.app.Application
import com.example.stopsapp.model.StopRepository
import com.example.stopsapp.model.local.StopDataBase

class AppStop : Application() {

    private val dataBase by lazy { StopDataBase.getDataBase(this) }
    val repository by lazy { StopRepository(dataBase.getStopDao(), dataBase.getRouteDao()) }

}