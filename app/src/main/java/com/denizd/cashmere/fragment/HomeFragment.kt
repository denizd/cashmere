package com.denizd.cashmere.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.denizd.cashmere.R
import com.denizd.cashmere.databinding.FragmentHomeBinding
import com.denizd.cashmere.util.viewBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closetButton.setOnClickListener {
            load(parentFragmentManager.findFragmentByTag("ClosetFragment") as? BaseFragment
                ?: ClosetFragment())
        }
        binding.settingsButton.setOnClickListener {
            load(parentFragmentManager.findFragmentByTag("SettingsFragment") as? BaseFragment
                ?: SettingsFragment())
        }
    }

    private fun load(fragment: BaseFragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment.name)
            .addToBackStack(fragment.name)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}