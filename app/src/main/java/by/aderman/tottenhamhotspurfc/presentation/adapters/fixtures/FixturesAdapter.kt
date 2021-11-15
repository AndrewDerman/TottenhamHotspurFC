package by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.aderman.tottenhamhotspurfc.databinding.ItemFixtureBinding
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture

class FixturesAdapter : RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder>() {

    inner class FixturesViewHolder(val binding: ItemFixtureBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixturesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFixtureBinding.inflate(inflater, parent, false)
        return FixturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FixturesViewHolder, position: Int) {
        val currentFixture = differ.currentList[position]
        with(holder.binding) {
            fixture = currentFixture
            root.setOnClickListener {
                onItemClickListener?.let { it(currentFixture.id) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Fixture>() {
        override fun areItemsTheSame(oldItem: Fixture, newItem: Fixture): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Fixture, newItem: Fixture): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)
}