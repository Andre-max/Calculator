package com.octagon_technologies.smart_calculator.di

import android.content.Context
import androidx.room.Room
import com.octagon_technologies.smart_calculator.repo.database.MathDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun providesRoom(@ApplicationContext context: Context) = Room
            .databaseBuilder(context, MathDatabase::class.java, "Math.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesMathDao(mathDatabase: MathDatabase) = mathDatabase.mathDao
}