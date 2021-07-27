package com.octagon_technologies.smart_calculator.presentation.binding_adapters.calculate

import androidx.databinding.BindingAdapter
import com.octagon_technologies.smart_calculator.R
import com.octagon_technologies.smart_calculator.model.KeyColor
import com.octagon_technologies.smart_calculator.model.Theme
import com.octagon_technologies.smart_calculator.presentation.util.getColor
import com.octagon_technologies.smart_calculator.presentation.views.KeyView

@BindingAdapter("keyText")
fun KeyView.changeText(text: String) { setText(text) }

@BindingAdapter("keyTheme", "keyColor")
fun KeyView.keySetup(theme: Theme, keyColor: KeyColor = KeyColor.NORMAL) {
    val resInt = when (keyColor) {
        KeyColor.NORMAL -> if (theme.isLight()) R.color.dark_black else R.color.white
        KeyColor.BLUE -> R.color.light_blue
        KeyColor.RED -> R.color.light_red
    }
    setTextColor(getColor(resources, resInt))


    setCardBackgroundColor(
        getColor(
            resources,
            if (theme.isLight()) R.color.grey else R.color.light_black
        )
    )

}