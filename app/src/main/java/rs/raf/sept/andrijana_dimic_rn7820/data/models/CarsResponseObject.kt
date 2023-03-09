package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CarsResponseObject (
    @Json(name = "cars")
    val cars :  List<CarsResponse> = listOf()
)