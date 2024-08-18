package com.example.hw7_1.ui.fragment.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw7_1.model.entity.LocationEntity
import com.example.hw7_1.repository.Repository
import kotlinx.coroutines.launch

class LocationViewModel (private val repository: Repository) : ViewModel() {

    val allData: LiveData<List<LocationEntity>> = repository.getAllLocations

    fun clearData() {
        viewModelScope.launch {
            repository.clearData()
        }
    }
}