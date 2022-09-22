package com.owl_laugh_at_wasted_time.mydictionary.di

import com.owl_laugh_at_wasted_time.mydictionary.data.database.InDictionaryRepository
import com.owl_laugh_at_wasted_time.mydictionary.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepository(impl: InDictionaryRepository): DictionaryRepository

}