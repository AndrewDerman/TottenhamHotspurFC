package by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemLineupAwayBinding
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.LineupPlayer

class AwayLineupAdapter : RecyclerView.Adapter<AwayLineupAdapter.AwayLineupViewHolder>() {

    inner class AwayLineupViewHolder(val binding: ItemLineupAwayBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AwayLineupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLineupAwayBinding.inflate(inflater, parent, false)
        return AwayLineupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AwayLineupViewHolder, position: Int) {
        holder.binding.player = differ.currentList[position]
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<LineupPlayer>() {
        override fun areItemsTheSame(oldItem: LineupPlayer, newItem: LineupPlayer): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LineupPlayer, newItem: LineupPlayer): Boolean =
            oldItem == newItem
    }

    var differ = AsyncListDiffer(this, diffCallback)
}