package com.example.hw7_1.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val location: String,
)

@Entity(tableName = "heroes")
data class HeroesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
)

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val image: String? = null,
    val location: String,
)

data class HomeModel(
    val title: String,
    val actionId: Int
)