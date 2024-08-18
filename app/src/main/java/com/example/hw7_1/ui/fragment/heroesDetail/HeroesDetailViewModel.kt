package com.example.hw7_1.ui.fragment.heroesDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.hw7_1.model.entity.HeroesEntity
import com.example.hw7_1.repository.Repository
import kotlinx.coroutines.launch

class HeroesDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _heroId = MutableLiveData<Int>()
    val hero: LiveData<HeroesEntity> = _heroId.switchMap { id ->
        repository.getHeroesById(id)
    }

    fun loadHistory(id: Int) {
        _heroId.value = id
    }

    fun saveHistory(hero: HeroesEntity) {
        viewModelScope.launch {
            if (hero.id == 0) {
                repository.insertHero(hero)
            } else {
                repository.updateHero(hero)
            }
        }
    }
}