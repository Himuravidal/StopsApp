package com.example.stopsapp.model.remote
import com.google.gson.annotations.SerializedName

data class ResponseStop( // principal object of stop.
    @SerializedName("has_next") val hasNext: Boolean,
    @SerializedName("page_number") val pageNumber: Int,
    @SerializedName("page_size") val pageSize: Int,
    @SerializedName("results") val results: List<StopDataRemote>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class StopDataRemote( //Stop object for data
    @SerializedName("stop_code") val stopCode: String,
    @SerializedName("stop_id") val stopId: String,
    @SerializedName("stop_name") val stopName: String
)

data class StopRoutesItem(  //Principal object of routes of the stop, there is a list of elements
    @SerializedName("route") val route: Route
)

data class Route(  // object for id and name of the route
    @SerializedName("route_color") val routeColor: String,
    @SerializedName("route_id") val routeId: String,
    @SerializedName("route_long_name") val routeLongName: String
)

