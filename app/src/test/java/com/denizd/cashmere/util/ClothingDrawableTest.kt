package com.denizd.cashmere.util

import com.denizd.cashmere.R
import org.junit.Assert.*
import org.junit.Test

class ClothingDrawableTest {

    @Test
    fun testEnumValue() {
        assertEquals(ClothingDrawable.BACKPACK.name, "BACKPACK")
        assertEquals(ClothingDrawable.BACKPACK.drawableId, R.drawable.backpack)
        assertEquals(ClothingDrawable.valueOf("BACKPACK").drawableId, R.drawable.backpack)
    }
}