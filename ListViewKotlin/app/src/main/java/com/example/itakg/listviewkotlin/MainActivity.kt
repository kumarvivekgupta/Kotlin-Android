package com.example.itakg.listviewkotlin

import android.content.Context
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_row.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setTitle("Animals")
        val names: Array<String> = resources.getStringArray(R.array.animalName)
        val dis: Array<String> =resources.getStringArray(R.array.animalDis)
        val images: Array<Int> = arrayOf(R.drawable.baboon,R.drawable.bulldog,R.drawable.panda,R.drawable.swallow_bird,R.drawable.white_tiger,R.drawable.zebra)
        var listOfAnimals: ArrayList<Animals> = arrayListOf<Animals>()

        for(i in 0..5)
        {
            listOfAnimals.add(Animals(names[i],dis[i],images[i]))
        }

        var adapter=myAdapter(this,listOfAnimals)
        listViewForAnimals.adapter=adapter





    }
   inner class myAdapter: BaseAdapter {

        var context:Context?=null


        var listOfAnimals: ArrayList<Animals> = arrayListOf<Animals>()
        constructor(context:Context,list:ArrayList<Animals>):super()
        {

            this.context=context
            this.listOfAnimals=list
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var animal=listOfAnimals[p0]
          var lI:LayoutInflater= context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var view:View=lI.inflate(R.layout.single_row,p2,false)
            view.animalName.text=animal.title
            view.animalDes.text=animal.dis
            view.animalImage.setImageResource(animal.image!!)

            view.setOnClickListener { display(p0) }
            return view

        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return 6
        }

    }
    fun display(pos:Int)
    {
        toast("item at position $pos")
    }

}
