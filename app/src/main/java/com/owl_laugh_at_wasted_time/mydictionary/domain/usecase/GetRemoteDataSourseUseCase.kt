package com.owl_laugh_at_wasted_time.mydictionary.domain.usecase

import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.DataModel
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetRemoteDataSourseUseCase @Inject constructor(private val repository:DictionaryRepository) {
    suspend fun getDataFromRemote(word: String): List<DataModel> =
        repository.getDataFromRemote(word)
}