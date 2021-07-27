package com.octagon_technologies.smart_calculator.presentation.util

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

fun getColor(res: Resources, @ColorRes resId: Int) =
    ResourcesCompat.getColor(res, resId, null)

fun getColor(context: Context, @ColorRes resId: Int) =
    getColor(context.resources, resId)