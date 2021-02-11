package com.example.stopsapp.model.local

import com.example.stopsapp.model.remote.ResponseStop
import com.example.stopsapp.model.remote.StopRoutesItem

fun fromRemoteToEntityStop(wrapper: ResponseStop) : List<StopData> {
    return wrapper.results.map {
        StopData(stopCode = it.stopCode, stopId = it.stopId, stopName = it.stopName)
    }
}

fun fromRemoteToEntityRoute(wrapper: List<StopRoutesItem>, stopId: String) : List<RouteData> {
    return wrapper.map {
        RouteData(it.route.routeColor, it.route.routeId, it.route.routeLongName, stopId)
    }
}