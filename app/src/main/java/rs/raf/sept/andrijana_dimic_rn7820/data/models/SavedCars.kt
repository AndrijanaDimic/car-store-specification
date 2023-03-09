package rs.raf.sept.andrijana_dimic_rn7820.data.models

import java.util.*


class SavedCars (
    val id : Long,
    val username: String,
    val car : String,
    val car_model : String,
    val car_color : String,
    val car_model_year : String,
    val car_vin : String,
    val price : String,
    val availability : Boolean,
    val date: Date = Date()
){}