package com.example.mobil_oblig_4.network

import com.example.mobil_oblig_4.model.Bike
import com.example.mobil_oblig_4.model.Brand
import com.example.mobil_oblig_4.model.BikeSize
import retrofit2.http.GET

interface ApiService {

    @GET("brands")
    suspend fun getBrands(): List<Brand>

    @GET("roadBikeSizes")
    suspend fun getRoadBikeSizes(): List<BikeSize>

    @GET("mountainBikeSizes")
    suspend fun getMountainBikeSizes(): List<BikeSize>

    @GET("hybridBikeSizes")
    suspend fun getHybridBikeSizes(): List<BikeSize>

    @GET("gravelBikeSizes")
    suspend fun getGravelBikeSizes(): List<BikeSize>

    @GET("kidsBikeSizes")
    suspend fun getKidsBikeSizes(): List<BikeSize>

    @GET("roadBikes")
    suspend fun getRoadBikes(): List<Bike>

    @GET("mountainBikes")
    suspend fun getMountainBikes(): List<Bike>

    @GET("hybridBikes")
    suspend fun getHybridBikes(): List<Bike>

    @GET("gravelBikes")
    suspend fun getGravelBikes(): List<Bike>

    @GET("kidsBikes")
    suspend fun getKidsBikes(): List<Bike>
}
