package com.rakarguntara.weatheringapp.di

import android.content.Context
import androidx.room.Room
import com.rakarguntara.weatheringapp.BuildConfig
import com.rakarguntara.weatheringapp.network.ApiService
import com.rakarguntara.weatheringapp.room.FavoriteWeatherDao
import com.rakarguntara.weatheringapp.room.WeatherLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFavoriteWeatherDao(weatherLocalDatabase: WeatherLocalDatabase): FavoriteWeatherDao
    = weatherLocalDatabase.favoriteWeatherDao()

    @Singleton
    @Provides
    fun provideWeatherLocalDatabase(@ApplicationContext context: Context): WeatherLocalDatabase
    = Room.databaseBuilder(context, WeatherLocalDatabase::class.java, "weather_local_database")
        .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideApiService(): ApiService{
        val loggingInterceptor = if(BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).client(client).build()
        return retrofit.create(ApiService::class.java)
    }
}