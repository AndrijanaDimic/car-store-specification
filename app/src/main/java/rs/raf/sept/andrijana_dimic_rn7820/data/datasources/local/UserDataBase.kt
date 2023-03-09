package rs.raf.sept.andrijana_dimic_rn7820.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.avgust.andrijana_dimic_rn7820.data.datasources.converters.DateConverter
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarEntity
import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserEntity
import rs.raf.sept.andrijana_dimic_rn7820.data.models.UserResponse

@Database(
    entities = [UserEntity::class, CarEntity::class],
    version = 7,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class UserDataBase : RoomDatabase(){
    abstract fun getUserDao(): UserDao
}