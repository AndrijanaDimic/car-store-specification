package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class UserResponse (
    @ColumnInfo(name = "Data")
    val data: User,
    @ColumnInfo(name = "Message")
    val message: String,
    @ColumnInfo(name = "UserId")
    val userId: String,
    @ColumnInfo(name = "CreatedAt")
    val createdAt: Date,
    @ColumnInfo(name = "NextSteps")
    val nextSteps: String,
    @ColumnInfo(name = "EmailValidation")
    val emailValidation: String,
    @ColumnInfo(name = "Subscribed")
    val subscribed: Boolean

)