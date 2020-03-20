package com.denizd.cashmere.util

import androidx.annotation.StringRes
import com.denizd.cashmere.R

enum class TemperatureRating(@StringRes val stringId: Int) {
    ALL_WEATHER(R.string.all_weather),
    FREEZING(R.string.freezing),
    COLD(R.string.cold),
    NORMAL(R.string.normal),
    WARM(R.string.warm),
    HOT(R.string.hot)
    ;
}