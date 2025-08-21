package com.example.machinecodingroundsample.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinecodingroundsample.data.repo.CatBreedRepository
import com.example.machinecodingroundsample.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(private val catBreedRepository: CatBreedRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchCatBreeds()
    }
    fun fetchCatBreeds() {
        viewModelScope.launch {
            try {
               catBreedRepository.getCatBreedsList().collect { breeds ->
                   _uiState.value = UiState.Success(breeds)
               }
            }catch (e: Exception){
                _uiState.value = UiState.Error(e.localizedMessage ?: "Something went wrong")
            }
        }
    }
}