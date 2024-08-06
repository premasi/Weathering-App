package com.rakarguntara.weatheringapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakarguntara.weatheringapp.models.FavoriteModelLocalResponse
import com.rakarguntara.weatheringapp.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val localRepository: LocalRepository): ViewModel() {
    private val _favList = MutableStateFlow<List<FavoriteModelLocalResponse>>(emptyList())
    val favList = _favList.asStateFlow()

    private val _fav = MutableStateFlow<FavoriteModelLocalResponse?>(null)
    val fav: StateFlow<FavoriteModelLocalResponse?> = _fav.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getFavorites().distinctUntilChanged()
                .collect{listFav ->
                    if(listFav.isNotEmpty()){
                        _favList.value = listFav
                    }
                }
        }
    }

    fun getFavoriteById(city: String) = viewModelScope.launch {
        _fav.value = localRepository.getFavoriteById(city)
    }

    fun insertFavorite(favoriteModelLocalResponse: FavoriteModelLocalResponse) = viewModelScope.launch {
        localRepository.insertFavorite(favoriteModelLocalResponse)
        getFavoriteById(city = favoriteModelLocalResponse.city)
    }

    fun updateFavorite(favoriteModelLocalResponse: FavoriteModelLocalResponse) = viewModelScope.launch {
        localRepository.updateFavorite(favoriteModelLocalResponse)
    }

    fun deleteAllFavorite() = viewModelScope.launch {
        localRepository.deleteAllFavorite()
    }

    fun deleteFavoriteById(city: String) = viewModelScope.launch {
        localRepository.deleteFavoriteById(city)
        getFavoriteById(city = city)
    }


}