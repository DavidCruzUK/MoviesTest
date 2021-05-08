package uk.co.davidcruz.moviestest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import uk.co.davidcruz.moviestest.databinding.RowItemBinding
import uk.co.davidcruz.service.datamodel.DataItem

class ItemViewHolder(private val binding: RowItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        movie: DataItem,
        listener: (id: Int) -> Unit
    ) {
        with(binding) {
            titleTV.text = movie.title
            root.setOnClickListener { listener(movie.id) }
        }
    }
}
