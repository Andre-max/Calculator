package com.octagon_technologies.smart_calculator.presentation.views

import android.content.Context
import android.util.TypedValue
import androidx.annotation.DimenRes

fun Context.getSspDimen(@DimenRes resId: Int) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        resources.getDimension(resId),
        resources.displayMetrics
    )

fun Context.getSdpDimen(@DimenRes resId: Int) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        resources.getDimension(resId),
        resources.displayMetrics
    )