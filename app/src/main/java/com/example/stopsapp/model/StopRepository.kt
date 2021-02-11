package com.example.stopsapp.model

import com.example.stopsapp.model.local.RouteDao
import com.example.stopsapp.model.local.StopDao

class StopRepository(private val stopDao: StopDao, private val routeDao: RouteDao) {
}