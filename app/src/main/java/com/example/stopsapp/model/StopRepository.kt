package com.example.stopsapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stopsapp.model.local.*
import com.example.stopsapp.model.remote.RemoteClient
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class StopRepository(private val stopDao: StopDao, private val routeDao: RouteDao) {

    private val service = RemoteClient.getRetroService() //Retrofit client
    private val errorMessage = MutableLiveData<String>() // expose Error messages

    fun getAllStopData(): Flow<List<StopData>> {
        return stopDao.getAllStopData()
    }

    fun getAllRouteData(stopId: String): Flow<List<RouteData>> {
        return routeDao.getAllStopData(stopId)
    }

    fun exposeErrorMessages(): LiveData<String> {
        return errorMessage
    }

    suspend fun fetchStopFromService(map: Map<String, String>) {
        Timber.d("REPO: fetch data of stops")
        try {
            val response = service.fetchStopData(map)
            when (response.isSuccessful) {
                true -> {
                    response.body()?.let {
                        stopDao.insertAllStop(fromRemoteToEntityStop(it))
                    }
                }
                false -> {
                    Timber.d("${response.code()} -- ${response.errorBody()}")
                    errorMessage.value = response.message()
                }
            }
        } catch (t: Exception) {
            Timber.e("$t")
            errorMessage.value = t.message
        }
    }


    suspend fun fetchRoutesFromService(stopId: String) {
        Timber.d("REPO: fetch routes of stops")
        try {
            val response = service.fetchRouteStop(stopId)
            when (response.isSuccessful) {
                true -> {
                    response.body()?.let {
                        routeDao.insertAllRoute(fromRemoteToEntityRoute(it, stopId))
                    }
                }
                false -> {
                    Timber.d("${response.code()} -- ${response.errorBody()}")
                    errorMessage.value = response.message()
                }
            }
        } catch (t: Exception) {
            Timber.e("$t")
            errorMessage.value = t.message
        }
    }

}