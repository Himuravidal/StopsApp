package com.example.stopsapp.model.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class StopAPITest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer

    private lateinit var service: StopAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StopAPI::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchStopData_happyCase() = runBlocking {
        //Given
        val map = mapOf<String, String>(
            Pair("center_lat", "-33.444087"),
            Pair("center_lon", "-70.653674"),
            Pair("limit", "20")
        )
        val responseListData = ResponseStop(
            hasNext = true,
            pageNumber = 1,
            totalPages = 795,
            totalResults = 795,
            pageSize = 20,
            results = listOf(StopDataRemote("PA215",
                "PA215",
                "PA215-Parada 5 / (M) La Moneda"))
        )
        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(responseListData)))

        //When
        val result = service.fetchStopData(map)
        //Then
        assertThat(result).isNotNull()
        assertThat(result.isSuccessful).isTrue()

        val message = result.body()
        assertThat(message).isNotNull()
        assertThat(message?.results).hasSize(1)
    }


    @Test
    fun fetchRouteData_happyCase() = runBlocking {
        //Given
        val responseList = listOf(StopRoutesItem(Route(routeColor = "FFFFFF", routeId = "229",
                    routeLongName = "(M) La Moneda - La Pintana")))
        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(responseList)))
        //When
            val result = service.fetchRouteStop("PA215")
        //Then
        assertThat(result).isNotNull()
        assertThat(result.isSuccessful).isTrue()

        val message = result.body()
        assertThat(message).isNotNull()
        assertThat(message).hasSize(1)
    }


}