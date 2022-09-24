package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.owl_laugh_at_wasted_time.mydictionary.R
import com.owl_laugh_at_wasted_time.mydictionary.databinding.FragmentWordsCheckBinding
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.BaseFragment
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.viewBinding

class WordsCheckFragment : BaseFragment(R.layout.fragment_words_check) {


    private val viewModel by viewModels<WordsCheckViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentWordsCheckBinding::bind)


    private var currentWordCount = 0
    private var words: List<WordItem> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkButtonsStatus()
        onClickListenersInit()
        viewModel.wordsLiveData.observe(viewLifecycleOwner) {
            setWords(it.filter { it.done==true })
        }

    }


    private fun setWords(list: List<WordItem>) {
        words = list
        binding.apply {
            if (list.size>0){
                noDataImageView.visibility = View.GONE
                textViewNoData.visibility = View.GONE
                dataGroup.visibility = View.VISIBLE
                previous.isEnabled = currentWordCount <= 0
                next.isEnabled = currentWordCount >= words.size
                chooseWord()
                checkButtonsStatus()
            }

        }
    }

    private fun onClickListenersInit() {
        binding.apply {
            previous.setOnClickListener {
                currentWordCount--
                checkButtonsStatus()
                chooseWord()
            }
            next.setOnClickListener {
                currentWordCount++
                checkButtonsStatus()
                chooseWord()
            }
            checkAnswer.setOnClickListener {
                answerWord.text = words[currentWordCount].translation
            }
        }
    }

    private fun checkButtonsStatus() {
        binding.apply {
            previous.isEnabled = currentWordCount > 0
            next.isEnabled = currentWordCount < words.size - 1
        }
    }

    private fun chooseWord() {
        binding.apply {
            try {
                questionWord.text = words[currentWordCount].word
                answerWord.text = ""
            } catch (e: Exception) {
            }
        }
    }

    companion object {
        fun newInstance(): WordsCheckFragment {
            return WordsCheckFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
}