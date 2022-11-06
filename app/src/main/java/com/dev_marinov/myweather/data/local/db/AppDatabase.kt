package com.dev_marinov.myweather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DetailEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}