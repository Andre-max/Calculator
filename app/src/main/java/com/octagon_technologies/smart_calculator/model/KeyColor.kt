package com.octagon_technologies.smart_calculator.model

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

enum class KeyColor {
    NORMAL,
    BLUE,
    RED;
}

fun getKeyColorFromXml(typedArray: TypedArray, @StyleableRes styleableRes: Int) =
    when (val keyColor = typedArray.getInt(styleableRes, 0)) {
        0 -> KeyColor.NORMAL
        1 -> KeyColor.BLUE
        2 -> KeyColor.RED
        else -> throw IndexOutOfBoundsException("keyColor is $keyColor")
    }
