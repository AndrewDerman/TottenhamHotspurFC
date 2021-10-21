package by.aderman.tottenhamhotspurfc.presentation.adapters.season

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemTopassistantBinding
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopAssistant


class AssistsAdapter : RecyclerView.Adapter<AssistsAdapter.TopAssistantViewHolder>() {

    inner class TopAssistantViewHolder(val binding: ItemTopassistantBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopAssistantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTopassistantBinding.inflate(inflater, parent, false)
        return TopAssistantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssistsAdapter.TopAssistantViewHolder, position: Int) {
        holder.binding.topAssistant = differ.currentList[position]
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffResult = object : DiffUtil.ItemCallback<PlayerTopAssistant>() {

        override fun areItemsTheSame(
            oldItem: PlayerTopAssistant,
            newItem: PlayerTopAssistant
        ): Boolean {
            return oldItem.photo == newItem.photo
        }

        override fun areContentsTheSame(
            oldItem: PlayerTopAssistant,
            newItem: PlayerTopAssistant
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffResult)
}