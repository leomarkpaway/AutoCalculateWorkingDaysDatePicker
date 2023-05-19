package com.leomarkpaway.numbertowordconverter.di

import com.leomarkpaway.autocalculateworkingdaysdatepicker.common.util.DatePickerHelper
import com.leomarkpaway.autocalculateworkingdaysdatepicker.domain.usecase.CalculateWorkingDaysUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCalculateWorkingDaysUseCase(datePickerHelper: DatePickerHelper) = CalculateWorkingDaysUseCase(datePickerHelper)
}