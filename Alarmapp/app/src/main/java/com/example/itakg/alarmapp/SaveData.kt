package com.example.itakg.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

/**
 * Created by itakg on 9/27/2017.
 */
class SaveData{
var context:Context?=null
    var mySharedPreferences:SharedPreferences?=null
    constructor(context:Context)
    {
        this.context=context
        this.mySharedPreferences=context.getSharedPreferences("myRef",Context.MODE_PRIVATE)
    }


    fun saveDataSafe(hr:Int,min:Int)
    {
        var editor=mySharedPreferences?.edit()
        editor?.putInt("Hour",hr)
        editor?.putInt("Minutes",min)
        editor?.commit()

    }

    fun getHour():Int{
        return mySharedPreferences!!.getInt("Hour",0)
    }
    fun getMinutes():Int
    {
        return mySharedPreferences!!.getInt("Minutes",0)
    }


    fun setAlarm()
    {
        var hr=getHour()
        var min=getMinutes()
        var calender:Calendar=Calendar.getInstance()
        calender.set(Calendar.HOUR,hr)
        calender.set(Calendar.MINUTE,min)
        calender.set(Calendar.SECOND,0)
        var am=context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent=Intent(context,MyBroadcastReciever::class.java)
        intent.putExtra("Message","Alarm Time")
        intent.action="com.itakgec.alarmapp"
        var pi=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)


        am.setRepeating(AlarmManager.RTC_WAKEUP,calender.timeInMillis,AlarmManager.INTERVAL_DAY,pi)

    }
}