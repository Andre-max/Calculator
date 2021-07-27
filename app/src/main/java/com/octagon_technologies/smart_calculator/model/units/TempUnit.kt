package com.octagon_technologies.smart_calculator.model.units

import kotlin.properties.Delegates

data class TempData(
    val value: Double,
    val tempUnit: TempUnit
)

enum class TempUnit {
    Celcius{ override var asKelvin = 283.0 },
    Fahrenheit{ override var asKelvin = 255.928 },
    Rankin{ override var asKelvin = 0.555556 },
    Reaumur, //{ override var asKelvin = Re × 1.25 + 273.15 },
    Kelvin{ override var asKelvin = 1.0 };

    open var asKelvin: Double by Delegates.notNull()
}

fun TempData.toKelvin(): TempData {
    val temp = when (tempUnit) {
        TempUnit.Celcius -> { value + 283 }
        TempUnit.Fahrenheit -> { (value - 32) * 0.5555555 + 273.15 } // Fix value - 32
        TempUnit.Rankin -> { value * 5/9  }
        TempUnit.Reaumur -> { value * 1.25 + 273.15 }
        TempUnit.Kelvin -> value
    }
    value - 32
    return TempData(temp, TempUnit.Kelvin)
}

fun TempData.fromKelvin(newTempUnit: TempUnit): TempData {
    val temp = when (tempUnit) {
        TempUnit.Celcius -> { value - 283 }
        TempUnit.Fahrenheit -> { (value - 273.15) * 9/5 + 32  } // Fix value - 32
        TempUnit.Rankin -> { value * 5/9  }
        TempUnit.Reaumur -> { value * 1.25 + 273.15 }
        TempUnit.Kelvin -> value
    }
    return TempData(temp, TempUnit.Kelvin)
}