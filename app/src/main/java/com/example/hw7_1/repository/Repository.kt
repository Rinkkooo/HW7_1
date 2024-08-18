package com.example.hw7_1.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.hw7_1.model.dao.HeroDao
import com.example.hw7_1.model.dao.HistoryDao
import com.example.hw7_1.model.dao.LocationDao
import com.example.hw7_1.model.entity.HeroesEntity
import com.example.hw7_1.model.entity.HistoryEntity
import com.example.hw7_1.model.entity.LocationEntity

class Repository (private val heroDao: HeroDao, private val historyDao: HistoryDao, private val locationDao: LocationDao){

    val getAllHeroes: LiveData<List<HeroesEntity>> = heroDao.getAll()

    val getAllHistory: LiveData<List<HistoryEntity>> = historyDao.getAll()

    val getAllLocations: LiveData<List<LocationEntity>> = locationDao.getAll()

    suspend fun insertHero(hero: HeroesEntity){
        heroDao.insertHero(hero)
    }

    suspend fun insertHistory(history: HistoryEntity){
        historyDao.insertStory(history)
    }

    suspend fun updateHistory(history: HistoryEntity){
        historyDao.update(history)
    }

    fun getHistoryById(id: Int): LiveData<HistoryEntity> =
        historyDao.getById(id)

    suspend fun clearData() =
        heroDao.clearAll()

    fun getHeroesById(id: Int): LiveData<HeroesEntity> =
        heroDao.getById(id)

    suspend fun updateHero(heroModel: HeroesEntity) =
        heroDao.update(heroModel)

    fun getLocationsById(id: Int): LiveData<LocationEntity> =
        locationDao.getById(id)

    suspend fun insertLocation(locationModel: LocationEntity) =
        locationDao.insertLocation(locationModel)

    suspend fun update(locationModel: LocationEntity) =
        locationDao.update(locationModel)

}