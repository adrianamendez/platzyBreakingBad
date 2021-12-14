package com.example.basicworktest.denise.di.modules

import android.app.Application
import androidx.room.Room
import com.example.basicworktest.denise.data.database.Dao
import com.example.basicworktest.denise.data.database.Database
import com.example.basicworktest.denise.data.database.RoomDataSource
import com.example.basicworktest.denise.data.datasource.source.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        RemoteDataSource(get())
    }
    single { providesDatabase(get()) }
    single { providesDao(get()) }
    single {
        RoomDataSource(get())
    }
}

fun providesDatabase(application: Application):Database =
    Room.databaseBuilder(application,Database::class.java,"database")
        .build()

fun providesDao(db:Database): Dao = db.dbDao()