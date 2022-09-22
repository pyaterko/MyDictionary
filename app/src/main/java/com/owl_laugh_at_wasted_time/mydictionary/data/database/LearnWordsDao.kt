package com.owl_laugh_at_wasted_time.mydictionary.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LearnWordsDao {

    @Query("SELECT * FROM LearnItemDBModelTable")
    fun getListWords(): LiveData<List<LearnItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(noteDBModel: LearnItemDBModel)

    @Query("DELETE FROM LearnItemDBModelTable WHERE word=:itemId")
    suspend fun deleteItem(itemId: String)

}
