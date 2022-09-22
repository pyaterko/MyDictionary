package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.owl_laugh_at_wasted_time.mydictionary.Initializer
import com.owl_laugh_at_wasted_time.mydictionary.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val component by lazy {
        Initializer.component(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView()
    }

    fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment)
            .commit()
    }
}