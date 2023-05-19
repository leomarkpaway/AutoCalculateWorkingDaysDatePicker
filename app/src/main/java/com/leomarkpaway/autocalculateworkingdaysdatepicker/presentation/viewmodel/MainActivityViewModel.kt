package com.leomarkpaway.autocalculateworkingdaysdatepicker.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.leomarkpaway.autocalculateworkingdaysdatepicker.domain.usecase.CalculateWorkingDaysUseCase
import com.leomarkpaway.numbertowordconverter.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val calculateWorkingDaysUseCase: CalculateWorkingDaysUseCase
) : BaseViewModel() {

    private val _result = MutableStateFlow(0)
    val result = _result.asStateFlow()

    private val _stringStartDate = MutableStateFlow<String?>(null)

    private val _endStartDate = MutableStateFlow<String?>(null)

    fun calculateWorkingDays() {
        if (_stringStartDate.value != null && _endStartDate.value != null) {
            calculateWorkingDaysUseCase(
                _stringStartDate.value.toString(),
                _endStartDate.value.toString()
            ).map { workingDays -> _result.value = workingDays }.launchIn(viewModelScope)
        }
    }

    fun updateStringStartDate(date: String) {
        launchDataOperation(flow = flowOf(date), onSuccess = {  _stringStartDate.value = it })
    }

    fun updateEndStartDate(date: String) {
        launchDataOperation(flow = flowOf(date), onSuccess = {  _endStartDate.value = it })
    }

}