package com.example.mobil_oblig_4.network

import retrofit2.http.GET
import com.example.mobil_oblig_4.model.Brand

interface ApiService {

    @GET("brands")
    suspend fun getBrands(): List<Brand>
}
