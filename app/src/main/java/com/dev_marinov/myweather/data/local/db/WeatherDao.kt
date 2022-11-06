package com.dev_marinov.myweather.data.local.db

import androidx.room.*
import com.dev_marinov.myweather.domain.Detail
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE city LIKE :city")
    fun getDetail(city: String): List<DetailEntity>
    ////////////////

    @Query("SELECT * FROM weather")
    fun getAll(): Flow<List<DetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(detail: List<DetailEntity>)

    @Delete
    fun delete(detailEntity: DetailEntity)

}