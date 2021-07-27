package com.octagon_technologies.smart_calculator.math

import androidx.core.text.isDigitsOnly
import com.octagon_technologies.smart_calculator.util.WrongInputException

class MiniMath {
    private val negative = Negative()

    fun turnToNegative(expression: String): String =
        negative.processExpression(expression)

    private inner class Negative {
        fun processExpression(expression: String): String {
            return if (expression.isEmpty()) "-"
            else {
                val hasSign = expression.any { it in listOf('+', '-', 'x', '÷') }

                if (hasSign) {
                    val values = expression.split('+', '-', 'x', '÷').toMutableList()
                    val isLastNegative = expression.contains("--")
                    val target = if (isLastNegative) "-${values.last()}" else values.last()
                    val newTarget = processDigitsOnly(target)
                    // 45 --4 and 45 - 4: Handle this
                    values[values.size - 1] = newTarget
                    values.joinToString()
                } else
                    processDigitsOnly(expression)
            }
        }

        private fun processDigitsOnly(expression: String) =
            if (expression.isDigitsOnly())
                "-$expression"
            else if (expression[0] == '-' && expression.substring(1).isDigitsOnly())
                expression.substring(1)
            else throw WrongInputException(expression)
    }

}

/*
Possible Scenarios:
    1. Negative:
    45 * -5
    -5
    -
 */