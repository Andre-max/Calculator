package com.octagon_technologies.smart_calculator.model

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

enum class Theme {
    Light{ override var value = "light" },
    Dark{ override var value = "dark" };

    open lateinit var value: String
    
    fun isLight() = this == Light
    fun isDark() = this == Dark
}