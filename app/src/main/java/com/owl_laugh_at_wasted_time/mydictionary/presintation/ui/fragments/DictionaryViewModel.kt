package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.DataModel
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.AddLearnUsecase
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.AddWordUseCase
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.GetListWordsUsecase
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.GetRemoteDataSourseUseCase
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.DataLoadingState
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.Error
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.Loading
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.Success
import kotlinx.coroutines.launch
import javax.inject.Inject

class DictionaryViewModel @Inject constructor(
    private val getRemoteDataSourseUseCase: GetRemoteDataSourseUseCase,
    private val getListWordsUsecase: GetListWordsUsecase,
    private val addWordUseCase: AddWordUseCase,
    private val addLearnUsecase: AddLearnUsecase
) : ViewModel() {

    private var _liveData = MutableLiveData<DataLoadingState<List<DataModel>>>()
    val liveData: LiveData<DataLoadingState<List<DataModel>>> = _liveData

    val listWords = getListWordsUsecase.getListWords()

    fun addWordItem(item: WordItem) {
        viewModelScope.launch {
            addWordUseCase.addWord(item)
        }
    }

    fun addWordLearn(item: WordItem) {
        viewModelScope.launch {
            addLearnUsecase.addLearn(item)
        }
    }

    fun getData(word: String) {
        _liveData.value = Loading()
        viewModelScope.launch {
            val list = getRemoteDataSourseUseCase.getDataFromRemote(word)
            if (list.isNotEmpty()) {
                list.forEach {
                    viewModelScope.launch {
                        addWordUseCase.addWord(
                            WordItem(
                                word = it.text!!,
                                translation = it.meanings?.get(0)?.translation?.translation!!
                            )
                        )
                    }
                }
                _liveData.postValue(Success(list))
            } else {
                _liveData.postValue(Error(IllegalAccessException()))
            }
        }
    }

}