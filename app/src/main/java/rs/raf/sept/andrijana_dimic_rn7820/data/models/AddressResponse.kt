package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AddressResponse (
    @Json(name = "street")
    val street : String,
    @Json(name = "street_name")
    val street_name : String,
    @Json(name = "city")
    val city : String,
    @Json(name = "state")
    val state : String,
    @Json(name = "country")
    val country : String,
    @Json(name = "country_code")
    val country_code : String,
    @Json(name = "postal_code")
    val postal_code : String,
    ){}