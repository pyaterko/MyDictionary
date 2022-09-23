package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.mydictionary.databinding.ItemDictionaryBinding
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {

    var onItemClickListener: ((WordItem) -> Unit)? = null

    var wordsItemList: List<WordItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDictionaryBinding.inflate(inflater, parent, false)
        return DictionaryViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val word = wordsItemList[position]
        holder.binding.root.tag = word
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(word)
        }
        with(holder.binding) {
            enWord.text = word.word
            ruWord.text = word.translation
        }
    }

    override fun getItemCount(): Int {
        return wordsItemList.size
    }

    class DictionaryViewHolder(
        val binding: ItemDictionaryBinding,
        val context: Context
    ) : RecyclerView.ViewHolder(binding.root)

}