package by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemStatisticBinding
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Statistics

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    inner class StatsViewHolder(val binding: ItemStatisticBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatsAdapter.StatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStatisticBinding.inflate(inflater, parent, false)
        return StatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsAdapter.StatsViewHolder, position: Int) {
        holder.binding.stats = differ.currentList[position]
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<Statistics>() {

        override fun areItemsTheSame(oldItem: Statistics, newItem: Statistics): Boolean = false

        override fun areContentsTheSame(oldItem: Statistics, newItem: Statistics): Boolean =
            oldItem == newItem
    }

    var differ = AsyncListDiffer(this, diffCallback)
}