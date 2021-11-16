package by.aderman.tottenhamhotspurfc.presentation.adapters.season

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.ItemStandingBinding
import by.aderman.tottenhamhotspurfc.domain.models.season.Standing

class TableAdapter : RecyclerView.Adapter<TableAdapter.StandingViewHolder>() {

    inner class StandingViewHolder(val binding: ItemStandingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStandingBinding.inflate(inflater, parent, false)
        return StandingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        holder.binding.standing = differ.currentList[position]
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<Standing>() {

        override fun areItemsTheSame(oldItem: Standing, newItem: Standing): Boolean =
            oldItem.team.id == newItem.team.id

        override fun areContentsTheSame(oldItem: Standing, newItem: Standing): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)
}