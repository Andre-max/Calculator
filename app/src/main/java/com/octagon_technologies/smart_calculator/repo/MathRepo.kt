package com.octagon_technologies.smart_calculator.repo

import com.octagon_technologies.smart_calculator.repo.database.LocalMathExpression
import com.octagon_technologies.smart_calculator.repo.database.MathDao
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MathRepo @Inject constructor(private val mathDao: MathDao) {

    suspend fun saveMathExpression(equation: String, result: String) {
        val localMathExpression = LocalMathExpression(
            timeStamp = System.currentTimeMillis(),
            equation = equation,
            result = result
        )
        mathDao.insertMathEquation(localMathExpression)
    }

    suspend fun fetchMathExpressions() =
        mathDao.fetchMathEquations().map { listOfMathExpression ->
            listOfMathExpression.map { it.toMathExpression() }
        }
}