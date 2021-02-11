package com.example.stopsapp.model.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StopDataBaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var stopDao: StopDao
    private lateinit var routeDao: RouteDao
    private lateinit var db: StopDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, StopDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        stopDao = db.getStopDao()
        routeDao = db.getRouteDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertEmptyStopData() = runBlocking {
        //Given
        val stopData = listOf<StopData>()
        //when
        stopDao.insertAllStop(stopData)
        //then
        stopDao.getAllStopData().asLiveData().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertEmptyRouteData() = runBlocking {
        //Given
        val routeData = listOf<RouteData>()
        //when
        routeDao.insertAllRoute(routeData)
        //then
        routeDao.getAllRouteData("").asLiveData().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertListStopData() = runBlocking {
        //Given
        val stopData = listOf(
            StopData(
                "PA215",
                "PA215",
                "PA215-Parada 5 / (M) La Moneda"
            )
        )
        //when
        stopDao.insertAllStop(stopData)

        //then
        val loadFromDB = stopDao.getAllStopData().first()
            assertThat(loadFromDB).isNotNull()
            assertThat(loadFromDB).hasSize(1)
    }


    @Test
    fun insertListRouteData() = runBlocking {
        //Given
        val routeData = listOf(
            RouteData(
                routeColor = "FFFFFF", routeId = "229",
                routeLongName = "(M) La Moneda - La Pintana", "PA215"
            )
        )
        //when
        routeDao.insertAllRoute(routeData)
        //then
        val loadFromDB = routeDao.getAllRouteData("PA215").first()
        assertThat(loadFromDB).isNotNull()
        assertThat(loadFromDB).hasSize(1)

    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat("com.example.stopsapp").isEqualTo(appContext.packageName)
    }

}