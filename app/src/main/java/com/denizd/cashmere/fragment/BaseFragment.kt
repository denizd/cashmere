package com.denizd.cashmere.fragment

import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.ScrollView
import androidx.annotation.LayoutRes
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.denizd.cashmere.R
import com.denizd.cashmere.sheet.BaseSheet
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

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

    protected open fun getGridColumnCount(config: Configuration, addIfTablet: Int = 1): Int {
        return when (config.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 1
            else -> 2
        } + if (resources.getBoolean(R.bool.isTablet)) addIfTablet else 0
    }

    protected fun ExtendedFloatingActionButton.addFabScrollListener(scrollView: View) {
        if (scrollView !is ScrollView && scrollView !is NestedScrollView && scrollView !is RecyclerView) {
            throw IllegalArgumentException("View ${scrollView::class.qualifiedName} is not a valid scrolling view")
        }
        scrollView.setOnScrollChangeListener { _, _, dy, _, doy ->
            when {
                dy > doy -> this.hide()
                dy < doy -> this.show()
            }
        }
    }
}