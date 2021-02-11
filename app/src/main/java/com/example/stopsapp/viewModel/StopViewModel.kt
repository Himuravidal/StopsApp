package com.example.stopsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stopsapp.model.StopRepository
import com.example.stopsapp.model.local.RouteData
import com.example.stopsapp.model.local.StopData
import kotlinx.coroutines.launch

class StopViewModel(private val repository: StopRepository) : ViewModel() {

    init {
        //This will be HardCoded for the test
        val map = mapOf<String, String>(
            Pair("center_lat", "-33.444087"),
            Pair("center_lon", "-70.653674"),
            Pair("limit", "20")
        )
        fetchStopData(map)
    }

    val currentStopDataFlow: LiveData<List<StopData>> = repository.getAllStopData().asLiveData()

    fun fetchStopData(map: Map<String, String>) = viewModelScope.launch {
        repository.fetchStopFromService(map)
    }

    // selected item
    private var stopSelected: String = ""

    fun fetchRouteData(idStop: String) = viewModelScope.launch {
        stopSelected = idStop
        repository.fetchRoutesFromService(idStop)
    }

    fun getRouteSelectedFromDB(): LiveData<List<RouteData>> =
        repository.getAllRouteData(stopSelected).asLiveData()

}