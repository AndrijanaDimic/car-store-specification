package rs.raf.sept.andrijana_dimic_rn7820.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CarsSearchResponseObject (
    @Json(name = "Cars")
    val cars :  List<CarsResponse> = listOf()
){}
