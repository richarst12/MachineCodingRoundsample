package com.example.machinecodingroundsample.di.mdoule

import android.content.Context
import com.example.machinecodingroundsample.MainApplication
import com.example.machinecodingroundsample.data.api.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule() {

//
//    @ApplicationContext
//    @Provides
//    fun provideContext(): Context {
//        return mainApplication
//    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://api.thecatapi.com/v1/"

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build()
            .create(
                NetworkService::class.java
            )

    }
}