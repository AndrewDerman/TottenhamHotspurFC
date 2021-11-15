package by.aderman.tottenhamhotspurfc.presentation.adapters.season

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemTopscorerBinding
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopScorer

class GoalsAdapter : RecyclerView.Adapter<GoalsAdapter.TopScorerViewHolder>() {

    inner class TopScorerViewHolder(val binding: ItemTopscorerBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopScorerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTopscorerBinding.inflate(inflater, parent, false)
        return TopScorerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalsAdapter.TopScorerViewHolder, position: Int) {
        holder.binding.topScorer = differ.currentList[position]
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffResult = object : DiffUtil.ItemCallback<PlayerTopScorer>() {

        override fun areItemsTheSame(oldItem: PlayerTopScorer, newItem: PlayerTopScorer): Boolean =
            oldItem.photo == newItem.photo

        override fun areContentsTheSame(
            oldItem: PlayerTopScorer,
            newItem: PlayerTopScorer
        ): Boolean = oldItem == newItem
    }

    var differ = AsyncListDiffer(this, diffResult)
}