package com.nadim.atlaspackaging.daily_production.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.MutableState
import java.util.*


class DatePicker(
    calendar: Calendar,
){
    private val year: Int = calendar.get(Calendar.YEAR)
    private val month: Int = calendar.get(Calendar.MONTH)
    private val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    fun getDatePickerDialog(context:Context,date: MutableState<String>) : DatePickerDialog {
        return DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                date.value = "$dayOfMonth/${month+1}/$year"
            }, year, month, day
        )
    }
}