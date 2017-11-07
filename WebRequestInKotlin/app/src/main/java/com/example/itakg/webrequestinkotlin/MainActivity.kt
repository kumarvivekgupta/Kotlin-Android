package com.example.itakg.webrequestinkotlin

import android.app.VoiceInteractor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    val APIKey = "&appid=f9324bceed9d507daa01563e94062d2b";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun getWeather(view: View)
    {

        val cityName=cityName.text.toString()

        val url=baseURL+cityName+APIKey
        var jor:JsonObjectRequest= JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener<JSONObject> { response ->
                  Log.i("Response",""+response)
                try{
                    var Main:String=response.getString("main")
                    var jso:JSONObject= JSONObject(Main)
                    var tmp=jso.getString("temp");
                    weather.text=tmp
                     }
                    catch (e:Exception){}

                },
                Response.ErrorListener {error ->
                     Log.i("Error",""+error)
                    weather.text=error.toString()

                } )
        MySingelton.getInstance(this).addToRequestQue(jor)

    }
}
