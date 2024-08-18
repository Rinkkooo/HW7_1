package com.example.hw7_1.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hw7_1.model.entity.HeroesEntity
import com.example.hw7_1.model.entity.HistoryEntity
import com.example.hw7_1.model.entity.LocationEntity

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(hero: HeroesEntity)

    @Query("SELECT * FROM heroes WHERE id = :id")
    fun getById(id: Int): LiveData<HeroesEntity>

    @Update
    suspend fun update(hero: HeroesEntity)

    @Query("DELETE FROM heroes")
    suspend fun clearAll()

    @Query("SELECT * FROM heroes")
    fun getAll(): LiveData<List<HeroesEntity>>
}

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getAll(): LiveData<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: HistoryEntity)

    @Update
    suspend fun update(history: HistoryEntity)

    @Query("DELETE FROM history")
    suspend fun clearAll()

    @Query("SELECT * FROM history WHERE id = :id")
    fun getById(id: Int): LiveData<HistoryEntity>
}

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    fun getAll(): LiveData<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Update
    suspend fun update(location: LocationEntity)

    @Query("DELETE FROM locations")
    suspend fun clearAll()

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getById(id: Int): LiveData<LocationEntity>
}
