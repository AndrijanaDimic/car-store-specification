package rs.raf.sept.andrijana_dimic_rn7820.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LogInUser (
    @Json(name = "username")
    val firstName: String,
    @Json(name = "Password")
    val password: String,
    @Json(name = "verified")
    val verified: Boolean,
){}