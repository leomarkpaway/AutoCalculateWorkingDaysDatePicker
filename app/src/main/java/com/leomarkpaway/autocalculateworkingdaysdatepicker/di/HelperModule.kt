package com.leomarkpaway.numbertowordconverter.di

import com.leomarkpaway.autocalculateworkingdaysdatepicker.common.util.DatePickerHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HelperModule {

    @Provides
    @Singleton
    fun provideDatePickHelper() = DatePickerHelper

}