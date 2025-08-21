package com.example.machinecodingroundsample.ui.base

import com.example.machinecodingroundsample.data.model.CatBreedResponse

sealed class UiState {
    object Loading : UiState()
    data class Success(val catBreedList: List<CatBreedResponse>) : UiState()
    data class Error(val errorMessage: String) : UiState()
}