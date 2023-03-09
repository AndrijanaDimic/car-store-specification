package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SellerResponse (
    @Json(name = "id")
    val id : Long,
    @Json(name = "first_name")
    val first_name : String,
    @Json(name = "last_name")
    val last_name : String,
    @Json(name = "email")
    val email : String,
    @Json(name = "gender")
    val gender :  String,
    @Json(name = "birthdate")
    val birthdate : String,
    @Json(name = "company_name")
    val company_name : String,
    @Json(name = "department")
    val department : String,
    @Json(name = "job_title")
    val job_title : String,

    @Json(name = "address")
    val address :  List<AddressResponse> = listOf(),

    @Json(name = "phone")
    val phone : String,
    @Json(name = "avatar")
    val avatar : String,
    @Json(name = "email_verified")
    val email_verified : Boolean,
    @Json(name = "password")
    val password : String,
    @Json(name = "last_login")
    val last_login : String,
    @Json(name = "last_login_ip")
    val last_login_ip : String,
    @Json(name = "subscribed")
    val subscribed : Boolean,
)