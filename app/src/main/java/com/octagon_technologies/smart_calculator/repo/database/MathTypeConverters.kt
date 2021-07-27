package com.octagon_technologies.smart_calculator.repo.database

import androidx.room.TypeConverter
import org.json.JSONObject

class MathTypeConverters {

    @TypeConverter
    internal fun localMathExpressionToString(localMathExpression: LocalMathExpression) =
        JSONObject()
            .put("id", localMathExpression.id)
            .put("timeStamp", localMathExpression.timeStamp)
            .put("equation", localMathExpression.equation)
            .put("result", localMathExpression.result)
            .toString()

    @TypeConverter
    internal fun stringToLocalMathExpression(data: String) =
        JSONObject(data).let { jsonObject ->
            LocalMathExpression(
                id = jsonObject.getInt("id"),
                timeStamp = jsonObject.getLong("timeStamp"),
                equation = jsonObject.getString("equation"),
                result = jsonObject.getString("result")
            )
        }
}
