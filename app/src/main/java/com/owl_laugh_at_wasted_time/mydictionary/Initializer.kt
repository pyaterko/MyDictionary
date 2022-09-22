package com.owl_laugh_at_wasted_time.mydictionary

import android.content.Context
import com.owl_laugh_at_wasted_time.mydictionary.di.AppComponent
import com.owl_laugh_at_wasted_time.mydictionary.di.DaggerAppComponent


object Initializer {
    fun component(context: Context): AppComponent {
        return DaggerAppComponent.factory().create(context)
    }
}