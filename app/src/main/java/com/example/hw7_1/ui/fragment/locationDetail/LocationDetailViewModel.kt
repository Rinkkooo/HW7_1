package com.example.hw7_1.ui.fragment.locationDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.hw7_1.model.entity.LocationEntity
import com.example.hw7_1.repository.Repository
import kotlinx.coroutines.launch

class LocationDetailViewModel(private val repository: Repository) : ViewModel() {
    private val _locationId = MutableLiveData<Int>()
    val location: LiveData<LocationEntity> = _locationId.switchMap { id ->
        repository.getLocationsById(id)
    }

    fun loadLocation(id: Int) {
        _locationId.value = id
    }

    fun saveLocation(locationModel: LocationEntity) {
        viewModelScope.launch {
            if (locationModel.id == 0) {
                repository.insertLocation(locationModel)
            } else {
                repository.update(locationModel)
            }
        }
    }
}