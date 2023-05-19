package com.leomarkpaway.autocalculateworkingdaysdatepicker.domain.usecase

import com.leomarkpaway.autocalculateworkingdaysdatepicker.common.util.DatePickerHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalculateWorkingDaysUseCase @Inject constructor(private val datePickerHelper: DatePickerHelper) {

    operator fun invoke(startStringDate: String, endStringDate: String): Flow<Int> = flow {
        try {
            val convertedNumber = datePickerHelper.calculateWorkingDays(startStringDate, endStringDate)
            emit(convertedNumber)
        } catch (e: Exception) { e.printStackTrace() }
    }
}