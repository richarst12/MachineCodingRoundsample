package com.example.machinecodingroundsample.data.repo

import android.util.Log
import com.example.machinecodingroundsample.data.api.NetworkService
import com.example.machinecodingroundsample.data.model.CatBreedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatBreedRepository @Inject constructor(private val networkService: NetworkService){

    fun getCatBreedsList(): Flow<List<CatBreedResponse>> {

        return flow {
            try {
                emit(networkService.getCatBreedsList())
            } catch (exception: Exception) {
                Log.e("CatBreedRepository", "Error fetching cat breeds", exception)
                throw exception // rethrow if you want ViewModel/UI to handle it
            }
        }.flowOn(Dispatchers.IO)
    }
}