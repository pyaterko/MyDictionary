package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments

import androidx.lifecycle.ViewModel
import com.owl_laugh_at_wasted_time.mydictionary.domain.usecase.GetListLearnUsecase
import javax.inject.Inject

class WordsCheckViewModel @Inject constructor(
    private val getListLearnUsecase: GetListLearnUsecase
) : ViewModel() {

    val wordsLiveData = getListLearnUsecase.getListLearn()


//
//    fun getWordsList(type: LessonType) {
//        doWork {
//            val answer = wordsSelectInteractor.getWordsList(type)?.map {
//                if (type.isEnAnswer) createEnToRU(it) else createRUToEn(it)
//            }
//            if (!answer.isNullOrEmpty()) {
//                wordsLiveData.postValue(answer.shuffled())
//            }
//        }
//    }
//
//    private fun createEnToRU(word: WordModel): WordForCheckModel {
//        return WordForCheckModel().apply {
//            question = word.en
//            answer = word.ru
//        }
//    }
//
//    private fun createRUToEn(word: WordModel): WordForCheckModel {
//        return WordForCheckModel().apply {
//            question = word.ru
//            answer = word.en
//        }
//    }

}