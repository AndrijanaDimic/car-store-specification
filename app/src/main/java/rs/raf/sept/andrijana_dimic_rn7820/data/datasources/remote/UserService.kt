package rs.raf.sept.andrijana_dimic_rn7820.data.datasources.remote

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.internal.operators.completable.CompletableAmb
import retrofit2.http.*
import rs.raf.sept.andrijana_dimic_rn7820.data.models.*
import java.util.*

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("signup")
    fun signup(@Body user : User): Single<UserResponse>

    @POST("signup")
    fun login(@Body user : LogInUser): Completable

    @GET ("cars/")
    fun getCars(@Query("limit") limit: Int = 1000): Observable<CarsResponseObject>

    @GET ("users/{id}")
    fun getSeller(@Path("id") id : Long): Single<SellerResponseObject>

    @POST("contactus")
    fun contactus(@Body message: Message):Completable

    @GET("cars/name/{name}")
    fun searchByName(@Path("name") name : String): Observable<CarsSearchResponseObject>

    @GET("cars/color/{color}")
    fun searchByColor(@Path("color") color : String): Observable<CarsSearchResponseObject>

    @GET("cars/model/{model}")
    fun searchByModel(@Path("model") model : String): Observable<CarsSearchResponseObject>

    @GET("cars/year/{year}")
    fun searchByYear(@Path("year") year : String): Observable<CarsSearchResponseObject>

    @GET("cars/")
    fun getCarsPagination(@Query("page") page: Int, @Query("limit") limit: Int ) :  Observable<CarsResponseObject>
}