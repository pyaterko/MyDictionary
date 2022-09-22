package com.owl_laugh_at_wasted_time.mydictionary.domain.repository


import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.DataModel


interface DictionaryApiWorker {
    suspend fun getData(word: String): List<DataModel>
}