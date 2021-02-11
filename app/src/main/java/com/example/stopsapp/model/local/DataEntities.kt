package com.example.stopsapp.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stop_table")
data class StopData(
    val stopCode: String,
    @PrimaryKey
    val stopId: String,
    val stopName: String
)

@Entity(tableName = "route_table")
data class RouteData(  // object for id and name of the route
    val routeColor: String,
    @PrimaryKey
    val routeId: String,
    val routeLongName: String
)