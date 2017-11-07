package com.example.itakg.ageinkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





    }
    public fun findAge(view:View)
    {
        var dob:Int=dob.text.toString().toInt()
        var currentYear:Int=Calendar.getInstance().get(Calendar.YEAR)
        age.setText((currentYear-dob).toString())
    }
}
