package com.leomarkpaway.autocalculateworkingdaysdatepicker.presentation.ui

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.leomarkpaway.autocalculateworkingdaysdatepicker.R
import com.leomarkpaway.autocalculateworkingdaysdatepicker.common.util.collectLatestData
import com.leomarkpaway.autocalculateworkingdaysdatepicker.databinding.ActivityMainBinding
import com.leomarkpaway.autocalculateworkingdaysdatepicker.presentation.viewmodel.MainActivityViewModel
import com.leomarkpaway.numbertowordconverter.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main
    override val viewModel: MainActivityViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        onClickButton()
    }

    override fun subscribe() {
        super.subscribe()
        displayWorkingDaysResult()
    }

    private fun onClickButton() = with(binding) {
        startDateButton.setOnClickListener { showDatePickerDialog(this@MainActivity, startDateButton, null) }
        endDateButton.setOnClickListener { showDatePickerDialog(this@MainActivity,null, endDateButton) }
    }

    private fun displayWorkingDaysResult() {
        viewModel.result.collectLatestData(lifecycleScope) {
            binding.resultTextView.text = "Number of working days: $it"
        }
    }

    private fun showDatePickerDialog(context: Context, buttonStartDate: Button?, buttonEndDate: Button?) = with(binding) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val date = getDate(year, month, dayOfMonth)
                if (buttonStartDate != null) {
                    viewModel.updateStringStartDate(formatDate(date))
                    buttonStartDate.text = formatDate(date)
                }
                if (buttonEndDate != null) {
                    viewModel.updateEndStartDate(formatDate(date))
                    buttonEndDate.text = formatDate(date)
                }
                viewModel.calculateWorkingDays()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun getDate(year: Int, month: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.time
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return formatter.format(date)
    }
}