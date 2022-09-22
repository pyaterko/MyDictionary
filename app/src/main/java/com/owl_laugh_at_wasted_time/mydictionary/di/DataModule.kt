package com.owl_laugh_at_wasted_time.mydictionary.di

import android.content.Context
import androidx.room.Room
import com.owl_laugh_at_wasted_time.mydictionary.data.database.DictionaryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    companion object {

        @Singleton
        @Provides
        fun provideDataBase(context: Context) =
            Room.databaseBuilder(context, DictionaryDatabase::class.java, "dictionary_data_base")
                .fallbackToDestructiveMigration()
                .build()

        @Singleton
        @Provides
        fun provideDictionaryDao(
            context: Context
        ) =
            provideDataBase(context).dictionaryDao()

        @Singleton
        @Provides
        fun provideWordsDao(
            context: Context
        ) =
            provideDataBase(context).wordDao()


    }
}