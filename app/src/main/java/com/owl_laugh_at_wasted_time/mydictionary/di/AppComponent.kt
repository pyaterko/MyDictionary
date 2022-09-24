package com.owl_laugh_at_wasted_time.mydictionary.di

import android.content.Context
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.activity.MainActivity
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.DictionaryFragment
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.WordsCheckFragment
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.WordsSelectFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        WorkerModule::class,
        RepositoryModule::class,
        DataModule::class,
        ViewModelModule::class
    ]

)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance applicationContext: Context
        ): AppComponent
    }

    fun context(): Context
    fun inject(activity: MainActivity)
    fun inject(activity: DictionaryFragment)
    fun inject(activity: WordsSelectFragment)
    fun inject(activity: WordsCheckFragment)
}