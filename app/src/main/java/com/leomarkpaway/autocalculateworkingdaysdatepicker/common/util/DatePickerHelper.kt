package com.leomarkpaway.autocalculateworkingdaysdatepicker.common.util

import java.text.SimpleDateFormat
import java.util.*

object DatePickerHelper {
    
    fun calculateWorkingDays(startStringDate: String, endStringDate: String) : Int {
        try {
            val startDate = getDateFromButton(startStringDate)
            val endDate = getDateFromButton(endStringDate)
            var currentDate = startDate
            var workingDays = 0
            while (currentDate.time <= endDate.time) {
                val dayOfWeek = Calendar.getInstance().apply {
                    time = currentDate
                }.get(Calendar.DAY_OF_WEEK)
                if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                    workingDays++
                }
                val calendar = Calendar.getInstance().apply {
                    time = currentDate
                }
                calendar.add(Calendar.DATE, 1)
                currentDate = calendar.time
            }

            return workingDays
        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }
    }

    private fun getDateFromButton(StringDate: String): Date {
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return formatter.parse(StringDate)
    }
}