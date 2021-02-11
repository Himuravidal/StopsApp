package com.example.stopsapp.model.local

import com.example.stopsapp.model.remote.ResponseStop
import com.example.stopsapp.model.remote.Route
import com.example.stopsapp.model.remote.StopDataRemote
import com.example.stopsapp.model.remote.StopRoutesItem
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class MapperKtTest {

    @Test
    fun fromRemoteToEntityStop_HappyCase() {
        //Given
        val wrapper = ResponseStop(
            hasNext = true,
            pageNumber = 1,
            totalPages = 795,
            totalResults = 795,
            pageSize = 20,
            results = listOf(
                StopDataRemote(
                    "PA215",
                    "PA215",
                    "PA215-Parada 5 / (M) La Moneda"
                )
            )
        )
        //When
        val result = fromRemoteToEntityStop(wrapper)

        //Then
        assertThat(result).isNotEmpty()
        assertThat(result).isNotNull()
        assertThat(result).hasSize(1)

    }

    @Test
    fun fromRemoteToEntityRoute_HappyCase() {
        //Given
        val responseList = listOf(
            StopRoutesItem(
                Route(
                    routeColor = "FFFFFF", routeId = "229",
                    routeLongName = "(M) La Moneda - La Pintana"
                )
            )
        )
        //When
        val result = fromRemoteToEntityRoute(responseList, "PA215")
        //Then
        assertThat(result).isNotEmpty()
        assertThat(result).isNotNull()
        assertThat(result).hasSize(1)
    }
}