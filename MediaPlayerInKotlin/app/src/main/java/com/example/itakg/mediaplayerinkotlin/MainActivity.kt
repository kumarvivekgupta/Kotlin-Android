package com.example.itakg.mediaplayerinkotlin

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_row.*
import kotlinx.android.synthetic.main.single_row.view.*
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    var mp:MediaPlayer?=null
    var list= ArrayList<ListOfSongs>()
    override fun onRestart() {
        super.onRestart()
        mp!!.reset()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
        mp = MediaPlayer()
        mp!!.reset()
        var mytracking=mySongTrack()
        mytracking.start()
        var window=this.window
        window.statusBarColor=ContextCompat.getColor(this,R.color.status)
        supportActionBar!!.title="Media Player"
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#912641")))

    }

    inner class MyAdapter :BaseAdapter{
        var context:Context?=null
        var list =ArrayList<ListOfSongs>()
        constructor(context:Context,list:ArrayList<ListOfSongs>):super()
        {
          this.context=context
            this.list=list
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView=inflator.inflate(R.layout.single_row,p2,false)
            var list2=list[p0]
            myView.title.text=list2.title
            myView.singer.text=list2.singer
            myView.play.setOnClickListener(View.OnClickListener {


                   if (myView.play.text.equals("Stop")) {
                       mp!!.stop()
                       myView.play.text = "Start"
                   } else {


                       try {

                           mp!!.reset()
                           progress.progress=0
                           mp!!.setDataSource(list2.url)
                           mp!!.prepare()
                           mp!!.start()
                           myView.play.text = "Stop"
                           progress.max=mp!!.duration
                       } catch (ex: Exception) {
                       }
                   }

            })
            return myView
        }

        override fun getItem(p0: Int): Any {

            return list[p0]
        }

        override fun getItemId(p0: Int): Long {

            return p0.toLong()
        }

        override fun getCount(): Int {
            return list.size
        }


    }
    inner  class  mySongTrack():Thread(){


        override fun run() {
            while(true){
                try{
                    Thread.sleep(1000)
                }catch (ex:Exception){}

                runOnUiThread {

                    if (mp!=null){
                        progress.progress = mp!!.currentPosition
                    }
                }
            }

        }
    }
    fun checkPermission()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
           if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
           {
               requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),123)
           }
        }
        loadSongs()
    }

     override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

         when (requestCode) {
             123 ->

                 if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                     loadSongs()

                 } else {
                     Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show()
                 }


             else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)

         }
     }

    fun loadSongs()
    {
        val allSongsURI=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selections=MediaStore.Audio.Media.IS_MUSIC+"!=0"
        val cursor = contentResolver.query(allSongsURI, null, selections, null, null)
        if(cursor!=null)
        {
            if(cursor!!.moveToFirst())
            {
                do{
                    val songURI=cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val songName=cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    val singer=cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    list.add(ListOfSongs(songName,singer,songURI))



                }while(cursor!!.moveToNext())
            }
            cursor.close()
            var adptr=MyAdapter(this,list)
            listOfSongs.adapter=adptr

        }
    }
}














