package com.example.inventorymanangementsystem

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import androidx.annotation.Nullable
import java.util.*

class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        val mCalendar: Calendar = Calendar.getInstance()
        val year: Int = mCalendar.get(Calendar.YEAR)
        val month: Int = mCalendar.get(Calendar.MONTH)
        val dayOfMonth: Int = mCalendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            getActivity(),
            getActivity() as OnDateSetListener?,
            year,
            month,
            dayOfMonth
        )
    }

}