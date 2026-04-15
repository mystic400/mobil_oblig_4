package com.example.mobil_oblig_4

import com.example.mobil_oblig_4.network.ApiService
import com.example.mobil_oblig_4.data.NetworkBikeRepository
import com.example.mobil_oblig_4.data.BikeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bikeRepository: BikeRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:3000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService =
        retrofit.create(ApiService::class.java)

    override val bikeRepository: BikeRepository by lazy {
        NetworkBikeRepository(apiService)
    }
}