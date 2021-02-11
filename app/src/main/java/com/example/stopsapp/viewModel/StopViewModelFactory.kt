package com.example.stopsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stopsapp.model.StopRepository

class StopViewModelFactory(private val repository: StopRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StopViewModel::class.java)) {
            return StopViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkow viemodel class")
    }

}