package com.owl_laugh_at_wasted_time.mydictionary.domain.repository

import androidx.lifecycle.LiveData
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.DataModel
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem


interface DictionaryRepository {
    fun getListWords(): LiveData<List<WordItem>>
    fun getListLearn(): LiveData<List<WordItem>>
    suspend fun addWord(item: WordItem)
    suspend fun deleteItem(itemId: String)
    suspend fun addLearn(item: WordItem)
    suspend fun deleteLearn(itemId: String)
    suspend fun getDataFromRemote(word: String): List<DataModel>

}