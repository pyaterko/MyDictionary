package com.owl_laugh_at_wasted_time.mydictionary.data.database


import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryApiWorker
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryRepository
import javax.inject.Inject

class InDictionaryRepository @Inject constructor(
    private val apiWorker: DictionaryApiWorker,
    private val mapper: DictionaryMapper,
    private val mapperLearn: LearnMapper,
    private val dictionaryDao: DictionaryDao,
    private val learnWordsDao: LearnWordsDao,
) : DictionaryRepository {

    override fun getListWords(): LiveData<List<WordItem>> = Transformations.map(
        dictionaryDao.getListWords()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun addWord(item: WordItem) {
        dictionaryDao.addItemNote(mapper.mapEntityToDbModel(item))
    }

    override suspend fun deleteItem(itemId: String) {
        dictionaryDao.deleteItem(itemId)
    }

    override fun getListLearn(): LiveData<List<WordItem>> = Transformations.map(
        learnWordsDao.getListWords()
    ) {
        mapperLearn.mapListDbModelToListEntity(it)
    }

    override suspend fun addLearn(item: WordItem) {
        learnWordsDao.addItem(mapperLearn.mapEntityToDbModel(item))
    }

    override suspend fun deleteLearn(itemId: String) {
        learnWordsDao.deleteItem(itemId)
    }

    override suspend fun getDataFromRemote(word: String) =
        apiWorker.getData(word)

}