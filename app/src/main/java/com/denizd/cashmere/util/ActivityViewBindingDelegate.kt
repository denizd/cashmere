package com.denizd.cashmere.util

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

// written by Zhuinden (props to them!)
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }