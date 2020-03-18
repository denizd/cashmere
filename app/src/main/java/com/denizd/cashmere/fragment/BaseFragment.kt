package com.denizd.cashmere.fragment

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.denizd.cashmere.sheet.BaseSheet

open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    val name: String = this::class.simpleName ?: ""

    private lateinit var _context: Context

    // only valid after onAttach
    override fun getContext(): Context = _context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context
    }

    protected fun openBottomSheet(sheet: BaseSheet) {
        sheet.setTargetFragment(this, 42)
        sheet.show(parentFragmentManager, sheet.name)
    }
}