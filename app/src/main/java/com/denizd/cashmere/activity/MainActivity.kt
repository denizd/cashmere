package com.denizd.cashmere.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.denizd.cashmere.R
import com.denizd.cashmere.database.AppRepository
import com.denizd.cashmere.databinding.ActivityMainBinding
import com.denizd.cashmere.fragment.BaseFragment
import com.denizd.cashmere.fragment.ClosetFragment
import com.denizd.cashmere.fragment.HomeFragment
import com.denizd.cashmere.fragment.SettingsFragment
import com.denizd.cashmere.util.viewBinding
import com.denizd.cashmere.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var viewModel: MainViewModel

    private val fragmentTypes: Map<Int, String> = mapOf(
        R.id.home to "HomeFragment",
        R.id.closet to "ClosetFragment",
        R.id.settings to "SettingsFragment"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.setWindowAnimations(R.style.WindowAnimation)
        // as MainViewModel holds a reference to AppRepository.Companion.instance,
        // AppRepository has to be instantiated before MainViewModel
        AppRepository.init(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        if (supportFragmentManager.fragments.size == 0) {
            loadFragment(with (R.id.home) {
                fragmentTypes[this] ?: throw IllegalArgumentException("Fragment for id $this not found")
            })
        }
    }

    override fun recreate() {
        super.recreate()
        applyAppTheme()
    }

    private fun applyAppTheme() {
        AppCompatDelegate.setDefaultNightMode(when (viewModel.getAppTheme()) {
            0 -> AppCompatDelegate.MODE_NIGHT_NO
            1 -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        })
    }

    // TODO obviously!
    private fun getNewFragment(type: String): BaseFragment = when (type) {
        "HomeFragment" -> HomeFragment()
        "ClosetFragment" -> ClosetFragment()
        "SettingsFragment" -> SettingsFragment()
        else -> throw IllegalArgumentException("Fragment $type does not exist")
    }

    private fun loadFragment(type: String): Boolean {
        val fragment = supportFragmentManager.findFragmentByTag(type) as? BaseFragment ?: getNewFragment(type)

        if (fragment.lifecycle.currentState != Lifecycle.State.INITIALIZED) return false

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment.name)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        return true
    }
}