package rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.sept.andrijana_dimic_rn7820.data.models.CarPresentation
import rs.raf.sept.andrijana_dimic_rn7820.databinding.LayoutSearchedcarsBinding
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.diff.SearchCarDiffCallback
import rs.raf.sept.andrijana_dimic_rn7820.ui.home.recyler.viewholder.SearchCarViewHolder


class SearchCarAdapter : ListAdapter<CarPresentation, SearchCarViewHolder>(SearchCarDiffCallback()) {

    var onClick: ((CarPresentation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCarViewHolder {
        val itemBinding = LayoutSearchedcarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchCarViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: SearchCarViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener(View.OnClickListener() {
            onClick?.let { it1 -> it1(item) }
            notifyItemChanged(position)
        })
    }


}
