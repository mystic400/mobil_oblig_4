package com.example.mobil_oblig_4

import com.example.mobil_oblig_4.network.ApiService
import com.example.mobil_oblig_4.data.NetworkBikeRepository
import com.example.mobil_oblig_4.data.BikeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// definerer vi hvilke dependencies vi har, et repository
interface AppContainer {
    val bikeRepository: BikeRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:3000/"

//setter opp Retrofit med base URL til JSON-serveren.
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //ApiService til å hente data fra serveren.
    private val apiService: ApiService =
        retrofit.create(ApiService::class.java)
//oppretter repository og sender inn apiService.
//
//Vi bruker by lazy, som betyr at repository kun opprettes når det trengs.

    override val bikeRepository: BikeRepository by lazy {
        NetworkBikeRepository(apiService)
    }
}