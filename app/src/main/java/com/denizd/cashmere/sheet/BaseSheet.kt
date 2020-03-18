package com.denizd.cashmere.sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseSheet(@LayoutRes private val layoutId: Int) : BottomSheetDialogFragment() {

    val name: String = this::class.simpleName ?: ""

    private lateinit var _context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context
    }

    // only valid after onAttach
    override fun getContext(): Context = _context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId, container)

    protected fun openBottomSheet(sheet: BaseSheet, targetFragment: Fragment = this) {
        sheet.setTargetFragment(targetFragment, 42)
        sheet.show(parentFragmentManager, sheet.name)
    }
}