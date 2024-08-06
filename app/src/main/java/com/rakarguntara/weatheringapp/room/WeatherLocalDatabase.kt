package com.rakarguntara.weatheringapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rakarguntara.weatheringapp.models.FavoriteModelLocalResponse

@Database(entities = [FavoriteModelLocalResponse::class], version = 1, exportSchema = false)
abstract class WeatherLocalDatabase: RoomDatabase() {
    abstract fun favoriteWeatherDao() : FavoriteWeatherDao
}