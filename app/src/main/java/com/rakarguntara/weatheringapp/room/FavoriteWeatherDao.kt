package com.rakarguntara.weatheringapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rakarguntara.weatheringapp.models.FavoriteModelLocalResponse
import com.rakarguntara.weatheringapp.models.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteWeatherDao {
    @Query("SELECT * FROM fav_table")
    fun getFavorites(): Flow<List<FavoriteModelLocalResponse>>

    @Query("SELECT * FROM fav_table WHERE city = :city")
    suspend fun getFavoriteById(city: String): FavoriteModelLocalResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteModelLocalResponse: FavoriteModelLocalResponse)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteModelLocalResponse: FavoriteModelLocalResponse)

    @Query("DELETE FROM fav_table")
    suspend fun deleteAllFavorite()

    @Query("DELETE FROM fav_table WHERE city = :city")
    suspend fun deleteFavoriteById(city: String)

    @Query("SELECT * FROM settings_table")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DELETE FROM settings_table")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: Unit)
}