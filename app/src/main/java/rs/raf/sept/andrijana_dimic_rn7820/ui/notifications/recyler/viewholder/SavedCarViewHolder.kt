package rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.data.models.SavedCars
import rs.raf.sept.andrijana_dimic_rn7820.databinding.LayoutCarsBinding
import rs.raf.sept.andrijana_dimic_rn7820.databinding.LayoutSavedcarsBinding

class SavedCarViewHolder(private val itemBinding: LayoutSavedcarsBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(savedCars: SavedCars) {
        itemBinding.carModel.text = savedCars.car_model
        itemBinding.carModelYear.text = savedCars.car_model_year
        itemBinding.carVin.text = savedCars.car_vin
        itemBinding.car.text = savedCars.car
        itemBinding.availability.text = savedCars.availability.toString()

        if(savedCars.availability) {
            itemBinding.availability.text = "da"
            itemBinding.availability.setTextColor(Color.parseColor("#00FF00"))
        } else {
            itemBinding.availability.text = "ne"
            itemBinding.availability.setTextColor(Color.parseColor("#FF0000"))
        }
        itemBinding.date.text = savedCars.date.toString()
    }

}