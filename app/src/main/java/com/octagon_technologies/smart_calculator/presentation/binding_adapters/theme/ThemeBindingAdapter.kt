package com.octagon_technologies.smart_calculator.presentation.binding_adapters.theme

import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.octagon_technologies.smart_calculator.R
import com.octagon_technologies.smart_calculator.model.Theme

@BindingAdapter("getTextColor")
fun TextView.getTextColor(theme: Theme?) {
    val textColor =
        if (theme == Theme.Dark)
            ResourcesCompat.getColor(context.resources, android.R.color.white, null)
        else
            ResourcesCompat.getColor(context.resources, R.color.dark_black, null)

    setTextColor(textColor)
}


@BindingAdapter("getBackgroundColor")
fun View.getBackgroundColor(theme: Theme?) {
    val backgroundColor =
        if (theme == Theme.Dark)
            ResourcesCompat.getColor(context.resources, R.color.dark_black, null)
        else
            ResourcesCompat.getColor(context.resources, android.R.color.white, null)

    setBackgroundColor(backgroundColor)
}

@BindingAdapter("getLighterBackgroundShade")
fun View.getLighterBackgroundShade(theme: Theme?) {
    val lighterShade =
        if (theme == Theme.Dark)
            ResourcesCompat.getColor(context.resources, R.color.light_black, null)
        else
            ResourcesCompat.getColor(context.resources, R.color.grey, null)

    setBackgroundColor(lighterShade)
}