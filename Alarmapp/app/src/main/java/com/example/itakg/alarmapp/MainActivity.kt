package com.example.itakg.alarmapp


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val save=SaveData(applicationContext)
        alarmTime.text =  save.getHour().toString()+ " : " + save.getMinutes().toString()
        startAlarm.setOnClickListener {
            var fm: FragmentManager = supportFragmentManager
            var dialog: TimePickerFragment = TimePickerFragment()
            dialog.show(fm, "My Dialog")


        }
    }

    fun setTime(hour: Int, min: Int) {
        alarmTime.text = hour.toString() + " : " + min.toString()
        val save=SaveData(applicationContext)
        save.saveDataSafe(hour,min)
        save.setAlarm()
    }
}
