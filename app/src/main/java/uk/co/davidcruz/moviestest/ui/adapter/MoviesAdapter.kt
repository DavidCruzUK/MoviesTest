package uk.co.davidcruz.moviestest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.co.davidcruz.moviestest.databinding.RowItemBinding
import uk.co.davidcruz.service.datamodel.DataItem
import kotlin.properties.Delegates

class MoviesAdapter(
    items: List<DataItem> = emptyList(),
    private val listener: (id: Int) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    var items: List<DataItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: RowItemBinding =
            RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}