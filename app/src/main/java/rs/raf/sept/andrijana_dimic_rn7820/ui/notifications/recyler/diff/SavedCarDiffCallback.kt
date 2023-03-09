package rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.data.models.SavedCars

class SavedCarDiffCallback : DiffUtil.ItemCallback<SavedCars>() {

    override fun areItemsTheSame(oldItem: SavedCars, newItem: SavedCars): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SavedCars, newItem: SavedCars): Boolean {
        return oldItem.car == newItem.car
    }


}