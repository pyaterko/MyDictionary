package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.AddLearnUsecase
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.DeleteLearnUsecase
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.GetListLearnUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordsSelectViewModel @Inject constructor(
    private val addLearnUsecase: AddLearnUsecase,
    private val getListLearnUsecase: GetListLearnUsecase,
    private val deleteLearnUsecase: DeleteLearnUsecase

) : ViewModel() {

    val listLearn = getListLearnUsecase.getListLearn()

    fun addLearnItem(item: WordItem) {
        viewModelScope.launch {
            addLearnUsecase.addLearn(item)
        }
    }

    fun deleteWordLearn(item: WordItem) {
        viewModelScope.launch {
            deleteLearnUsecase.deleteLearn(item.word)
        }
    }

}