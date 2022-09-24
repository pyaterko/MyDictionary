package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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
    var checkClick: ((View) -> Unit)? = null

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
        val item = items[position]
        holder.binding.wordSelect.tag = item
        holder.bind(item)
        holder.binding.wordSelect.setOnCheckedChangeListener { _, isChecked ->
                item.done = isChecked
             checkClick?.invoke(holder.binding.wordSelect)

        }

    }

    inner class WordsSelectViewHolder(val binding: ItemWordsSelectBinding) :
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
