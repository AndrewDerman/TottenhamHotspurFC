package by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemEventAwayBinding
import by.aderman.tottenhamhotspurfc.databinding.ItemEventHomeBinding
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Event

class EventsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class HomeEventsViewHolder(val binding: ItemEventHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class AwayEventsViewHolder(val binding: ItemEventAwayBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position].team.isHome) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            HomeEventsViewHolder(ItemEventHomeBinding.inflate(inflater, parent, false))
        } else {
            AwayEventsViewHolder(ItemEventAwayBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = differ.currentList[position]
        when (holder) {
            is HomeEventsViewHolder -> holder.binding.event = item
            is AwayEventsViewHolder -> holder.binding.event = item
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean = false

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)
}