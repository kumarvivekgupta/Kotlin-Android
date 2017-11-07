package com.example.itakg.alarmapp
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by itakg on 9/27/2017.
 */
class MyBroadcastReciever :BroadcastReceiver()
{
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1!!.action.equals("com.itakgec.alarmapp"))
        {
            var b:String=p1.extras.getString("Message")
            Toast.makeText(p0,b,Toast.LENGTH_SHORT).show()
        }
        else if(p1!!.action.equals("android.intent.action.BOOT_COMPLETED"))
        {
            val save=SaveData(p0!!)
            save.setAlarm()

        }
    }

}