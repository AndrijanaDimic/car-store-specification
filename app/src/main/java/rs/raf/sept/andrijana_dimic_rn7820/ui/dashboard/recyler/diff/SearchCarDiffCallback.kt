package rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation

class SearchCarDiffCallback : DiffUtil.ItemCallback<CarPresentation>() {

    override fun areItemsTheSame(oldItem: CarPresentation, newItem: CarPresentation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarPresentation, newItem: CarPresentation): Boolean {
        return oldItem.car == newItem.car
    }


}