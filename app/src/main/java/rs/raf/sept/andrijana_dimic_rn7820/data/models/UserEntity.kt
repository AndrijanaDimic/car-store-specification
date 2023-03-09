package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val mobile : String,
    val country: String,
    val active: Boolean
)