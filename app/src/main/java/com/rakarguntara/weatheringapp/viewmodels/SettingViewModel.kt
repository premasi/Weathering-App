package com.rakarguntara.weatheringapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakarguntara.weatheringapp.models.Unit
import com.rakarguntara.weatheringapp.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val localRepository: LocalRepository) : ViewModel() {
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getUnits().distinctUntilChanged()
                .collect{ list ->
                    if(list.isNotEmpty()){
                        _unitList.value = list
                    }

                }
        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch { localRepository.insertUnit(unit) }
    fun updateUnit(unit: Unit) = viewModelScope.launch { localRepository.updateUnit(unit) }
    fun deleteAllUnit() = viewModelScope.launch { localRepository.deleteAllUnit() }
}