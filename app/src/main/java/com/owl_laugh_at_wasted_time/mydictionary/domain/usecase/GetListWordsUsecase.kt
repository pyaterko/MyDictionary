package com.owl_laugh_at_wasted_time.mydictionary.domain.usecase

import androidx.lifecycle.LiveData
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetListWordsUsecase @Inject constructor(private val repository: DictionaryRepository) {
    fun getListWords(): LiveData<List<WordItem>> =
        repository.getListWords()
}