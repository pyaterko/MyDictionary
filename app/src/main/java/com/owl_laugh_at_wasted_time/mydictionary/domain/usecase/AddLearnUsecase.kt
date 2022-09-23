package com.owl_laugh_at_wasted_time.mydictionary.domain.usecase

import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryRepository
import javax.inject.Inject

class AddLearnUsecase @Inject constructor(private val repository: DictionaryRepository) {
    suspend fun addLearn(item: WordItem){
        repository.addLearn(item)
    }
}