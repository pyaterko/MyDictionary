package com.owl_laugh_at_wasted_time.mydictionary.di

import com.owl_laugh_at_wasted_time.mydictionary.data.api.DefaultApiWorker
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryApiWorker
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface WorkerModule {

    @Singleton
    @Binds
    fun bindDictionaryApiWorker(impl: DefaultApiWorker): DictionaryApiWorker
}