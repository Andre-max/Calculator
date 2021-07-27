package com.octagon_technologies.smart_calculator.math

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MiniMathTest {

    private val miniMath = MiniMath()

    @Test
    fun turnToNegative_withEmptyInput_returnsNegative() {
        val output = miniMath.turnToNegative("")
        assertThat(output).isEqualTo("-")
    }

    @Test
    fun turnToNegative_withDigitsOnly_returnsNegativeDigits() {
        val output = miniMath.turnToNegative("100")
        assertThat(output).isEqualTo("-100")
    }

    @Test
    fun turnToNegative_withNegativeDigits_returnsPositive() {
        val output = miniMath.turnToNegative("-200")
        assertThat(output).isEqualTo("200")
    }

    @Test
    fun turnToNegative_withNormalEquation_addsMinus() {
        val output = miniMath.turnToNegative("42*56")
        assertThat(output).isEqualTo("42*-56")
    }

    @Test
    fun turnToNegative_withTrailingSign_returnsNegative() {
        val output = miniMath.turnToNegative("20*")
        assertThat(output).isEqualTo("-")
    }

    @Test
    fun turnToNegative_withTrailingNegativeSign_returnsNegative() {
        val output = miniMath.turnToNegative("20-")
        assertThat(output).isEqualTo("20--")
    }
}