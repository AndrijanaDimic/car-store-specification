package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CarsResponse (
    @Json(name = "id")
    val id : Long,
    @Json(name = "car")
    val car : String,
    @Json(name = "car_model")
    val car_model : String,
    @Json(name = "car_color")
    val car_color : String,
    @Json(name = "car_model_year")
    val car_model_year : String,
    @Json(name = "car_vin")
    val car_vin : String,
    @Json(name = "price")
    val price : String,
    @Json(name = "availability")
    val availability : Boolean
){

}