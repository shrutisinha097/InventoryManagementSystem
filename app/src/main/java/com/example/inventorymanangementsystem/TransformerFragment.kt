package com.example.inventorymanangementsystem

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


class TransformerFragment : Fragment() {

    private lateinit var etType:EditText
    private lateinit var spinnerMake:Spinner
    private lateinit var txtDate:TextView
    private lateinit var etSerialNumber:EditText
    private lateinit var etCapacity:EditText
    private lateinit var txtEnergazationDate:TextView
    private lateinit var txtWarranty:TextView
    private lateinit var btnSave:Button
    private lateinit var btnReset:Button
    val myCalendar: Calendar = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_transformer, container, false)

        etType=view.findViewById(R.id.etType)
        spinnerMake=view.findViewById(R.id.spinnerMake)
        txtDate=view.findViewById(R.id.txtDate)
        etSerialNumber=view.findViewById(R.id.etSerialNumber)
        etCapacity=view.findViewById(R.id.etCapacity)
        txtEnergazationDate=view.findViewById(R.id.txtEnergazationDate)
        txtWarranty=view.findViewById(R.id.txtWarranty)
        btnSave=view.findViewById(R.id.btnSave)
        btnReset=view.findViewById(R.id.btnReset)

        updateLabel(txtWarranty)
        updateLabel(txtDate)
        updateLabel(txtEnergazationDate)

        btnSave.setOnClickListener {
            Toast.makeText(activity as Context,"$txtWarranty$txtDate$txtDate$etType$etSerialNumber$etCapacity",Toast.LENGTH_LONG).show()
        }
        btnReset.setOnClickListener {
            spinnerMake.setSelection(0)
            etType.setText("")
            etCapacity.setText("")
            etSerialNumber.setText("")

        }

        val zone = arrayListOf("Select Make","aashutosh","shambhaw","shubham","manish","ashok")

        val adapter = ArrayAdapter(
            activity as Context,
            R.layout.spinner_layout, zone
        )


        spinnerMake.adapter = adapter

        spinnerMake.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        return view
    }
    private fun updateLabel(txt:TextView) {

        val date =
            OnDateSetListener { p0, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel(txtWarranty)
            }

        txt.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                activity as Context,
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        })

        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        txt.text=(dateFormat.format(myCalendar.time))
    }

}