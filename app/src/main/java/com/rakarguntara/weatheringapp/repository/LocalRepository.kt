package com.rakarguntara.weatheringapp.repository

import com.rakarguntara.weatheringapp.models.FavoriteModelLocalResponse
import com.rakarguntara.weatheringapp.room.FavoriteWeatherDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(private val favoriteWeatherDao: FavoriteWeatherDao) {
    fun getFavorites(): Flow<List<FavoriteModelLocalResponse>> = favoriteWeatherDao.getFavorites()

    suspend fun getFavoriteById(city: String): FavoriteModelLocalResponse = favoriteWeatherDao.getFavoriteById(city)

    suspend fun insertFavorite(favoriteModelLocalResponse: FavoriteModelLocalResponse)
    = favoriteWeatherDao.insertFavorite(favoriteModelLocalResponse)

    suspend fun updateFavorite(favoriteModelLocalResponse: FavoriteModelLocalResponse)
    = favoriteWeatherDao.updateFavorite(favoriteModelLocalResponse)

    suspend fun deleteAllFavorite() = favoriteWeatherDao.deleteAllFavorite()

    suspend fun deleteFavoriteById(city: String) = favoriteWeatherDao.deleteFavoriteById(city)
}