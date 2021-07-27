package com.octagon_technologies.smart_calculator.repo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octagon_technologies.smart_calculator.model.MathExpression

@Entity(tableName = "localMathExpression")
data class LocalMathExpression(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeStamp: Long,
    val equation: String,
    val result: String
) {
    fun toMathExpression() =
        MathExpression(equation, result)
}