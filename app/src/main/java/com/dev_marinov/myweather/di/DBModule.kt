package com.dev_marinov.myweather.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.dev_marinov.myweather.data.local.db.AppDatabase
import com.dev_marinov.myweather.data.local.db.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase) : WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) : AppDatabase {
        val db = databaseBuilder(
            context,
            AppDatabase::class.java, "database-weather"
        ).fallbackToDestructiveMigration().build()
        return db
    }

}