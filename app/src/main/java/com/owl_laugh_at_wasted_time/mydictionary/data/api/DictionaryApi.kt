package com.owl_laugh_at_wasted_time.mydictionary.data.api

import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {
    @GET("words/search")
    suspend fun getData(@Query("search") word: String): List<DataModel>
}