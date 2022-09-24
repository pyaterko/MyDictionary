package com.owl_laugh_at_wasted_time.mydictionary.data.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM WordItemDBModelTable")
    fun getListWords(): LiveData<List<WordItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemNote(noteDBModel: WordItemDBModel)

    @Query("DELETE FROM WordItemDBModelTable WHERE word=:itemId")
    suspend fun deleteItem(itemId: String)
}