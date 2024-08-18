package com.example.hw7_1.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hw7_1.model.dao.HeroDao
import com.example.hw7_1.model.dao.HistoryDao
import com.example.hw7_1.model.dao.LocationDao
import com.example.hw7_1.model.entity.HeroesEntity
import com.example.hw7_1.model.entity.HistoryEntity
import com.example.hw7_1.model.entity.LocationEntity

@Database(
    entities = [HeroesEntity::class, LocationEntity::class, HistoryEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun locationDao(): LocationDao
    abstract fun heroesDao(): HeroDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE history_table ADD COLUMN location TEXT")
            }
        }
    }

}