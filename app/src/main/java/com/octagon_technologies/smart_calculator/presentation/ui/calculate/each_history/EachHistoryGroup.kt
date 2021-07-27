package com.octagon_technologies.smart_calculator.presentation.ui.calculate.each_history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.octagon_technologies.smart_calculator.R
import com.octagon_technologies.smart_calculator.databinding.EachHistoryGroupBinding
import com.octagon_technologies.smart_calculator.model.MathExpression
import com.octagon_technologies.smart_calculator.model.Theme
import com.xwray.groupie.databinding.BindableItem

class EachHistoryGroup(
    private val theme: LiveData<Theme>,
    private val lifecycleOwner: LifecycleOwner,
    private val mathExpression: MathExpression
) : BindableItem<EachHistoryGroupBinding>() {
    override fun bind(binding: EachHistoryGroupBinding, position: Int) {
        binding.lifecycleOwner = lifecycleOwner
        binding.theme = theme
        binding.mathExpression = mathExpression
    }

    override fun getLayout() = R.layout.each_history_group
}