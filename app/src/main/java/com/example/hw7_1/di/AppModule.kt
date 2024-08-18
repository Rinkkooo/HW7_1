package com.example.hw7_1.di

import androidx.room.Room
import com.example.hw7_1.model.database.AppDatabase
import com.example.hw7_1.repository.Repository
import com.example.hw7_1.ui.fragment.heroes.HeroesViewModel
import com.example.hw7_1.ui.fragment.heroesDetail.HeroesDetailViewModel
import com.example.hw7_1.ui.fragment.history.HistoryViewModel
import com.example.hw7_1.ui.fragment.historyDetail.HistoryDetailViewModel
import com.example.hw7_1.ui.fragment.location.LocationViewModel
import com.example.hw7_1.ui.fragment.locationDetail.LocationDetailViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        AppDatabase.getDatabase(get())
    }

    single {
        get<AppDatabase>().heroesDao()
    }

    single {
        get<AppDatabase>().locationDao()
    }

    single {
        get<AppDatabase>().historyDao()
    }

    factory {
        Repository(get(), get(), get())
    }

    viewModel {
        HistoryViewModel(get())
    }
    viewModel {
        HistoryDetailViewModel(get())
    }
    viewModel {
        HeroesViewModel(get())
    }
    viewModel {
        HeroesDetailViewModel(get())
    }
    viewModel {
        LocationViewModel(get())
    }
    viewModel {
        LocationDetailViewModel(get())
    }


}