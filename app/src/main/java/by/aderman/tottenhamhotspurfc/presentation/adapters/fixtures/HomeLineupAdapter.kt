package by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemLineupHomeBinding
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.LineupPlayer

class HomeLineupAdapter : RecyclerView.Adapter<HomeLineupAdapter.HomeLineupViewHolder>() {

    inner class HomeLineupViewHolder(val binding: ItemLineupHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeLineupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLineupHomeBinding.inflate(inflater, parent, false)
        return HomeLineupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeLineupViewHolder, position: Int) {
        holder.binding.player = differ.currentList[position]
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<LineupPlayer>() {
        override fun areItemsTheSame(oldItem: LineupPlayer, newItem: LineupPlayer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LineupPlayer, newItem: LineupPlayer): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallback)
}