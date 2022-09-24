package com.owl_laugh_at_wasted_time.mydictionary.di

import androidx.lifecycle.ViewModel
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.DictionaryViewModel
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.WordsCheckViewModel
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.WordsSelectViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DictionaryViewModel::class)
    fun bindDictionaryViewModel(viewModel: DictionaryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WordsSelectViewModel::class)
    fun bindWordsSelectViewModel(viewModel: WordsSelectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WordsCheckViewModel::class)
    fun bindWordsCheckViewModel(viewModel: WordsCheckViewModel): ViewModel
}