package com.example.stopsapp.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface StopAPI {

    @GET("stops")
    suspend fun fetchStopData(@QueryMap params: Map<String, String>) : Response<ResponseStop>

    @GET("stops/{stopId}/stop_routes")
    suspend fun fetchRouteStop(@Path("stopId") stopId: String): Response<List<StopRoutesItem>>
}