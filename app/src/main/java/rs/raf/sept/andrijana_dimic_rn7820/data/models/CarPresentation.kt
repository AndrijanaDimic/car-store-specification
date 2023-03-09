package rs.raf.sept.andrijana_dimic_rn7820.data.models

import androidx.room.ColumnInfo

class CarPresentation (
    val id : Long,
    val car : String,
    val car_model : String,
    val car_color : String,
    val car_model_year : String,
    val car_vin : String,
    val price : String,
    val availability : Boolean
    ){

}