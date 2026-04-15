import retrofit2.http.GET

interface ApiService {

    @GET("brands")
    suspend fun getBrands(): List<Brand>

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
