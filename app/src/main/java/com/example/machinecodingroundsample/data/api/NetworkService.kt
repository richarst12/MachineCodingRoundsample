package com.example.machinecodingroundsample.data.api

import com.example.machinecodingroundsample.data.model.CatBreedResponse
import com.example.machinecodingroundsample.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("breeds")
    suspend fun getCatBreedsList(): List<CatBreedResponse>

}