package rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.databinding.LayoutCarsBinding

class CarViewHolder(private val itemBinding: LayoutCarsBinding) : RecyclerView.ViewHolder(itemBinding.root) {


    fun bind(carPresentation: CarPresentation) {
        itemBinding.carModel.text = carPresentation.car_model
        itemBinding.carModelYear.text = carPresentation.car_model_year
        itemBinding.carVin.text = carPresentation.car_vin
        itemBinding.car.text = carPresentation.car
        itemBinding.availability.text = carPresentation.availability.toString()
        if(carPresentation.availability) {
            itemBinding.availability.text = "da"
            itemBinding.availability.setTextColor(Color.parseColor("#00FF00"))
        } else {
            itemBinding.availability.text = "ne"
            itemBinding.availability.setTextColor(Color.parseColor("#FF0000"))
        }

    }


}