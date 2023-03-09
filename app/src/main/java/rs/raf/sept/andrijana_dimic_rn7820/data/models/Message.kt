package rs.raf.sept.andrijana_dimic_rn7820.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Message(
    val first_name: String,
    val last_name : String,
    val message: String,
    val contact: String
){
}