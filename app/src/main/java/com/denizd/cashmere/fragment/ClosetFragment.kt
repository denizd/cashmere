package com.denizd.cashmere.fragment

import android.os.Bundle
import android.view.View
import com.denizd.cashmere.R
import com.denizd.cashmere.databinding.FragmentClosetBinding
import com.denizd.cashmere.util.viewBinding

class ClosetFragment : BaseFragment(R.layout.fragment_closet) {

    private val binding: FragmentClosetBinding by viewBinding(FragmentClosetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}