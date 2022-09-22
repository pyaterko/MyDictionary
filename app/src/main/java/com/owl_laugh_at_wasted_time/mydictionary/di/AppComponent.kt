package com.owl_laugh_at_wasted_time.mydictionary.di

import android.content.Context
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        WorkerModule::class,
        RepositoryModule::class,
        DataModule::class
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
}