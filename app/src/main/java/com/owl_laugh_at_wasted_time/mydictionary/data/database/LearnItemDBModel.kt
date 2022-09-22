package com.owl_laugh_at_wasted_time.mydictionary.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LearnItemDBModelTable")
data class LearnItemDBModel(
    @PrimaryKey
    val word: String,
    val translation: String,
    var done: Boolean = false
)