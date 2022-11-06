package com.dev_marinov.myweather.di

import com.dev_marinov.myweather.data.remote.DetailWeatherRepository
import com.dev_marinov.myweather.domain.ICitiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTownsRepository(detailWeatherRepository: DetailWeatherRepository): ICitiesRepository
}