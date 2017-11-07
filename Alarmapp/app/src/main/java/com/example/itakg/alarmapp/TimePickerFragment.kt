package com.example.itakg.alarmapp


import android.os.Build
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import kotlinx.android.synthetic.main.time_picker.*

/**
 * Created by itakg on 9/27/2017.
 */

class TimePickerFragment : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.time_picker, container, false)
        var setTime = view.findViewById<Button>(R.id.settime) as Button
        var timePicker = view.findViewById<TimePicker>(R.id.timePicker) as TimePicker
        setTime.setOnClickListener {
            var myActivity = activity as MainActivity
            if (Build.VERSION.SDK_INT >= 23)
                myActivity.setTime(timePicker.hour, timePicker.minute)
            else
                myActivity.setTime(timePicker.currentHour, timePicker.currentMinute)

            dismiss()

        }
        return view
    }
}
