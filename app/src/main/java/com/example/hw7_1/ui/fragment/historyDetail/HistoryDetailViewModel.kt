package com.example.hw7_1.ui.fragment.historyDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.hw7_1.model.entity.HistoryEntity
import com.example.hw7_1.repository.Repository
import kotlinx.coroutines.launch

class HistoryDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _historyId = MutableLiveData<Int>()
    val history: LiveData<HistoryEntity> = _historyId.switchMap { id ->
        repository.getHistoryById(id)
    }

    fun loadHistory(id: Int) {
        _historyId.value = id
    }

    fun saveHistory(history: HistoryEntity) {
        viewModelScope.launch {
            if (history.id == 0) {
                repository.insertHistory(history)
            } else {
                repository.updateHistory(history)
            }
        }
    }
}