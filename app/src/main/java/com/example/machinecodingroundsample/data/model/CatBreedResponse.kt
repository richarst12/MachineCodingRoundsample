package com.example.machinecodingroundsample.data.model

data class CatBreedResponse(
    val id: String,
    val name: String,
    val temperament: String?,
    val origin: String?,
    val description: String?,
    val life_span: String?
)