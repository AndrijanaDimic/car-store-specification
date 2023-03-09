package rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.databinding.LayoutCarsBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.diff.SearchCarDiffCallback
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.viewholder.CarViewHolder


class CarAdapter : ListAdapter<CarPresentation, CarViewHolder>(SearchCarDiffCallback()) {

    var onClick: ((CarPresentation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemBinding = LayoutCarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener(View.OnClickListener() {
            onClick?.let { it1 -> it1(item) }
            notifyItemChanged(position)
        })
    }


}
