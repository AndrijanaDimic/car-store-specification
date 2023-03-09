package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "mobile")
    val mobile: String,
    @Json(name = "country")
    val country: String
)