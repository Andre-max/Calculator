package com.octagon_technologies.smart_calculator.presentation.binding_adapters.calculate

import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.octagon_technologies.smart_calculator.R

@BindingAdapter("updateTheme")
fun ImageView.updateTheme(isSelected: Boolean) {
    val tint = ResourcesCompat.getColor(
        resources,
        if (isSelected) android.R.color.white else R.color.dark_black,
        null
    )
    val background = ResourcesCompat.getColor(
        resources,
        if (isSelected) R.color.light_black else R.color.grey,
        null
    )

    setColorFilter(tint)
    setBackgroundColor(background)
}