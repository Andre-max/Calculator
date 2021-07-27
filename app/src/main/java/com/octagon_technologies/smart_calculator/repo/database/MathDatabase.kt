package com.octagon_technologies.smart_calculator.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [LocalMathExpression::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [(MathTypeConverters::class)])
abstract class MathDatabase : RoomDatabase() {

    abstract val mathDao: MathDao

}