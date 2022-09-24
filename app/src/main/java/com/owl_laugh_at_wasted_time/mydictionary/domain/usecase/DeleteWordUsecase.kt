package com.owl_laugh_at_wasted_time.mydictionary.domain.usecase

import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryRepository
import javax.inject.Inject

class DeleteWordUsecase @Inject constructor(private val repository: DictionaryRepository) {
    suspend fun deleteItem(itemId: String) {
        repository.deleteItem(itemId)
    }
}