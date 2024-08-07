package com.rakarguntara.weatheringapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakarguntara.weatheringapp.models.FavoriteModelLocalResponse
import com.rakarguntara.weatheringapp.models.Unit

@Database(entities = [FavoriteModelLocalResponse::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherLocalDatabase: RoomDatabase() {
    abstract fun favoriteWeatherDao() : FavoriteWeatherDao
}