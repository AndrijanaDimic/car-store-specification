package rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.data.models.SavedCars
import rs.raf.sept.andrijana_dimic_rn7820.databinding.LayoutCarsBinding
import rs.raf.sept.andrijana_dimic_rn7820.databinding.LayoutSavedcarsBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.diff.SavedCarDiffCallback
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.viewholder.SavedCarViewHolder


class SavedCarAdapter : ListAdapter<SavedCars, SavedCarViewHolder>(SavedCarDiffCallback()) {

    var onClick: ((SavedCars) -> Unit)? = null
    var itemSavedCar: SavedCars? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCarViewHolder {
        val itemBinding = LayoutSavedcarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedCarViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: SavedCarViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        itemSavedCar = item
        holder.itemView.setOnClickListener(View.OnClickListener() {
            onClick?.let { it1 -> it1(item) }
            notifyItemChanged(position)
        })
    }

    override public fun getItem(position: Int): SavedCars {
        return super.getItem(position)
    }

}