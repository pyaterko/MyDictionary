package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.owl_laugh_at_wasted_time.mydictionary.Initializer
import com.owl_laugh_at_wasted_time.mydictionary.R
import com.owl_laugh_at_wasted_time.mydictionary.databinding.ActivityMainBinding
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base.viewBinding
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.DictionaryFragment
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.WordsCheckFragment
import com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.fragments.WordsSelectFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    val component by lazy {
        Initializer.component(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navigat = binding.mainActivityBottomNavigation
        navigat.setOnItemSelectedListener(::handleNavigationItemClick)
        launchFragment(DictionaryFragment.newInstance())
    }

    private fun handleNavigationItemClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.dictionaryFragment -> {
                launchFragment(DictionaryFragment.newInstance())
            }
            R.id.wordsSelectFragment -> {
                launchFragment(WordsSelectFragment.newInstance())
            }
            R.id.learnFragment -> {
                launchFragment(WordsCheckFragment.newInstance())
            }
        }
        return true
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, fragment)
            .commit()
    }
}