package com.owl_laugh_at_wasted_time.mydictionary.domain.entity

import com.google.gson.annotations.SerializedName

data class DataModel(
    @field:SerializedName("text") val text: String? = null,
    @field:SerializedName("meanings") val meanings: List<Meanings>? = null
)