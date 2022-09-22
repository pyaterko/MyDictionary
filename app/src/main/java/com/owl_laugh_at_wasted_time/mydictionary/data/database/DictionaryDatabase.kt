package com.owl_laugh_at_wasted_time.mydictionary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WordItemDBModel::class, LearnItemDBModel::class],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao
    abstract fun wordDao(): LearnWordsDao
}