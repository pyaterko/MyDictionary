package com.owl_laugh_at_wasted_time.mydictionary.data.api

import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.DataModel
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryApiWorker
import javax.inject.Inject

class DefaultApiWorker @Inject constructor(private val api: DictionaryApi) : DictionaryApiWorker {
    override suspend fun getData(word: String): List<DataModel> {
        return api.getData(word)
    }
}