package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.mydictionary.databinding.ItemWordsSelectBinding
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem


class WordsSelectAdapter : RecyclerView.Adapter<WordsSelectAdapter.WordsSelectViewHolder>() {

    private var items = mutableListOf<WordItem>()

    fun getData() = items

    fun setData(list: List<WordItem>) {
        items = list.toMutableList()
        notifyDataSetChanged()
    }

    var itemCheckClick: ((WordItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordsSelectAdapter.WordsSelectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWordsSelectBinding.inflate(inflater, parent, false)
        return WordsSelectViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WordsSelectViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class WordsSelectViewHolder(private val binding: ItemWordsSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WordItem) {
            binding.apply {
                wordSelect.setOnCheckedChangeListener { _, isChecked ->
                    item.done = isChecked
                    itemCheckClick?.invoke(item)
                }
                wordSelect.isChecked = item.done
                enWord.text = item.word
                ruWord.text = item.translation
            }
        }
    }
}
