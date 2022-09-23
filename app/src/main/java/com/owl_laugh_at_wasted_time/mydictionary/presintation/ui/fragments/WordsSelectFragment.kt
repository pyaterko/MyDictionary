package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.owl_laugh_at_wasted_time.mydictionary.R
import com.owl_laugh_at_wasted_time.mydictionary.databinding.FragmentWordsSelectBinding
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.viewBinding

class WordsSelectFragment : BaseFragment(R.layout.fragment_words_select) {


    private val viewModel by viewModels<WordsSelectViewModel> { viewModelFactory }
    private val wordsAdapter: WordsSelectAdapter by lazy { WordsSelectAdapter() }
    private val binding by viewBinding(FragmentWordsSelectBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            wordsList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = wordsAdapter
            }

            toUp.setOnClickListener {
                wordsList.scrollToPosition(0)
            }

            toDown.setOnClickListener {
                wordsList.scrollToPosition(wordsAdapter.itemCount - 1)
            }
        }
        viewModel.listLearn.observe(viewLifecycleOwner) {
            setWords(it.toList())
        }
        setupSwipe(binding.wordsList){
            viewModel.deleteWordLearn(wordsAdapter.getData()[it.adapterPosition])
        }
    }


    private fun setWords(list: List<WordItem>) {
        binding.noDataImageView.visibility = if (list.isNotEmpty()) View.GONE else View.VISIBLE
        binding.textViewNoData.visibility = if (list.isNotEmpty()) View.GONE else View.VISIBLE

        checkAllSelectedCheckBox(list)
        wordsAdapter.setData(list)

    }

    private fun checkAllSelectedCheckBox(list: List<WordItem>) {
        binding.apply {
            selectAll.setOnCheckedChangeListener(null)

            selectAll.isChecked = list.size == list.filter { it.done }.size

            selectAll.setOnCheckedChangeListener { _, isChecked ->
                wordsAdapter.getData().forEach { it.done = isChecked }
                wordsAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        fun newInstance(): WordsSelectFragment {
            return WordsSelectFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }

}