package com.example.hw7_1.ui.fragment.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw7_1.model.entity.HistoryEntity
import com.example.hw7_1.repository.Repository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel() {

    val allData:LiveData<List<HistoryEntity>> = repository.getAllHistory

    fun clearData(history: HistoryEntity) {
        viewModelScope.launch {
            repository.clearData()
        }
    }
}