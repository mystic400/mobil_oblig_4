package com.example.mobil_oblig_4.data

import com.example.mobil_oblig_4.R
import com.example.mobil_oblig_4.model.Bike
import com.example.mobil_oblig_4.model.Brand
import com.example.mobil_oblig_4.model.BikeSize
import com.example.mobil_oblig_4.model.BikeCategory
import com.example.mobil_oblig_4.network.ApiService

interface BikeRepository {
    suspend fun getBrands(): List<Brand>
    suspend fun getRoadBikeSizes(): List<BikeSize>
    suspend fun getMountainBikeSizes(): List<BikeSize>
    suspend fun getHybridBikeSizes(): List<BikeSize>
    suspend fun getGravelBikeSizes(): List<BikeSize>
    suspend fun getKidsBikeSizes(): List<BikeSize>
    suspend fun getRoadBikes(): List<Bike>
    suspend fun getMountainBikes(): List<Bike>
    suspend fun getHybridBikes(): List<Bike>
    suspend fun getGravelBikes(): List<Bike>
    suspend fun getKidsBikes(): List<Bike>
}

class NetworkBikeRepository(private val apiService: ApiService) : BikeRepository {
    override suspend fun getBrands(): List<Brand> = apiService.getBrands()
    override suspend fun getRoadBikeSizes(): List<BikeSize> = apiService.getRoadBikeSizes()
    override suspend fun getMountainBikeSizes(): List<BikeSize> = apiService.getMountainBikeSizes()
    override suspend fun getHybridBikeSizes(): List<BikeSize> = apiService.getHybridBikeSizes()
    override suspend fun getGravelBikeSizes(): List<BikeSize> = apiService.getGravelBikeSizes()
    override suspend fun getKidsBikeSizes(): List<BikeSize> = apiService.getKidsBikeSizes()

    override suspend fun getRoadBikes(): List<Bike> = 
        apiService.getRoadBikes().mapWithBrands(BikeCategory.Road, R.drawable.road_bike_24)
    
    override suspend fun getMountainBikes(): List<Bike> = 
        apiService.getMountainBikes().mapWithBrands(BikeCategory.Mountain, R.drawable.mountain_bike_24)
    
    override suspend fun getHybridBikes(): List<Bike> = 
        apiService.getHybridBikes().mapWithBrands(BikeCategory.Hybrid, R.drawable.hybrid_bike_24)
    
    override suspend fun getGravelBikes(): List<Bike> = 
        apiService.getGravelBikes().mapWithBrands(BikeCategory.Gravel, R.drawable.gravel_bike_24)
    
    override suspend fun getKidsBikes(): List<Bike> = 
        apiService.getKidsBikes().mapWithBrands(BikeCategory.KidsBike, R.drawable.kids_bike_24)

    private suspend fun List<Bike>.mapWithBrands(category: BikeCategory, imageRes: Int): List<Bike> {
        val brands = getBrands()
        return this.map { bike ->
            bike.copy(
                brand = brands.find { it.id == bike.brandId } ?: brands.firstOrNull() ?: Brand("", "Unknown", ""),
                bikeCategory = category,
                imageResource = imageRes
            )
        }
    }
}
