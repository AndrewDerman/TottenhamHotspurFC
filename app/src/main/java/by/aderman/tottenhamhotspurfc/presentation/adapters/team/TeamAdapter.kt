package by.aderman.tottenhamhotspurfc.presentation.adapters.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemPlayerBinding
import by.aderman.tottenhamhotspurfc.domain.models.team.Player

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlayerBinding.inflate(inflater, parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val currentPlayer = differ.currentList[position]
        with(holder.binding) {
            player = currentPlayer
            root.setOnClickListener {
                onItemClickListener?.let { it(currentPlayer) }
            }
        }
    }

    private var onItemClickListener: ((Player) -> Unit)? = null

    fun setOnItemClickListener(listener: (Player) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)
}
