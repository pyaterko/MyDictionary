package com.owl_laugh_at_wasted_time.mydictionary.domain.entity

data class WordItem(
    val word: String,
    val translation: String,
    var done: Boolean = false
)