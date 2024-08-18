package com.example.hw7_1.ui.fragment.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw7_1.model.entity.HeroesEntity
import com.example.hw7_1.repository.Repository
import kotlinx.coroutines.launch

class HeroesViewModel(private val repository: Repository) : ViewModel() {

    val allData: LiveData<List<HeroesEntity>> = repository.getAllHeroes

    fun clearAll() {
        viewModelScope.launch {
            repository.clearData()
        }
    }

}