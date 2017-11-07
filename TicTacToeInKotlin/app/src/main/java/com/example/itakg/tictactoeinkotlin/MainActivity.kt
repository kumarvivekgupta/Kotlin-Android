package com.example.itakg.tictactoeinkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var myCurrentPlayer:Int=0
     var myGameState:IntArray= intArrayOf(2,2,2,2,2,2,2,2,2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun imageClicked(view:View)
    {

        var imageView=view as ImageView
        var tag:Int=imageView.getTag().toString().toInt()
       // Toast.makeText(this,"$tag",Toast.LENGTH_SHORT).show()

        if(myGameState[tag]==2)
        {
            if(myCurrentPlayer==0)
            {
                imageView.setImageResource(R.drawable.cross)
                imageView.animate().rotation(360.0f)
                myCurrentPlayer=1
                 myGameState[tag]=0
                if(checkIntersection(0)==true)
                {
                    Toast.makeText(this,"Player 1 wins the game!!",Toast.LENGTH_SHORT).show()
                    for (i in 0..myGameState.size-1)
                    {
                        myGameState[i]=3;
                    }
                }

            }
            else
            {
                imageView.setImageResource(R.drawable.circle)
                imageView.animate().rotation(360.0f)
                myCurrentPlayer=0
                myGameState[tag]=1
                if(checkIntersection(1)==true)
                {
                    Toast.makeText(this,"Player 2 wins the game!!",Toast.LENGTH_SHORT).show()
                    for (i in 0..myGameState.size-1)
                    {
                        myGameState[i]=3;
                    }
                }

            }
        }
        else
        {
            Toast.makeText(this,"Not allowed!!",Toast.LENGTH_SHORT).show()
        }
    }

    public  fun playAgainTapped(view:View)
    {
        var image:ImageView
        for (i in 0..myGameState.size-1)
        {
            myGameState[i]=2
        }
        for(i in 0..gridOfButtons.childCount-1)
        {

            image=gridOfButtons.getChildAt(i) as ImageView
            image.setImageResource(R.mipmap.ic_launcher)
        }
        myCurrentPlayer=0
    }
    fun checkIntersection(i:Int):Boolean {
        if (myGameState[0] == i && myGameState[3] == i && myGameState[6] == i) {

            return true;
        } else if (myGameState[1] == i && myGameState[4] == i && myGameState[7] == i) {
            return true;
        } else if (myGameState[2] == i && myGameState[5] == i && myGameState[8] == i) {
            return true;
        } else if (myGameState[0] == i && myGameState[1] == i && myGameState[2] == i) {
            return true;
        } else if (myGameState[3] == i && myGameState[4] == i && myGameState[5] == i) {
            return true;
        } else if (myGameState[6] == i && myGameState[7] == i && myGameState[8] == i) {
            return true;
        } else if (myGameState[0] == i && myGameState[4] == i && myGameState[8] == i) {
            return true;
        } else if (myGameState[2] == i && myGameState[4] == i && myGameState[6] == i) {
            return true;
        } else
            return false;
    }

}
