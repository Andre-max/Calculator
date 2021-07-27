package com.octagon_technologies.smart_calculator.repo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MathDao {

    @Insert
    suspend fun insertMathEquation(localMathExpression: LocalMathExpression)

    @Query("SELECT * FROM localMathExpression ORDER BY timeStamp DESC")
    fun fetchMathEquations(): Flow<List<LocalMathExpression>>
}