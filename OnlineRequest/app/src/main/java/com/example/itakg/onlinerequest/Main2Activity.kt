package com.example.itakg.onlinerequest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    var dbRef= FirebaseDatabase.getInstance()
    var myRef=dbRef.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        IncomingRequests()

    }
    fun requestClicked(view:View)
    {
        myRef.child("Users").child(SplitString(requestName.text.toString())).child("Request").push().setValue(intent.extras.getString("Email"))

    }

    fun acceptClicked(view:View)
    {
        myRef.child("Users").child(SplitString(requestName.text.toString())).child("Request").push().setValue(intent.extras.getString("Email"))
    }
    fun  SplitString(str:String):String{
        var split=str.split("@")
        return split[0]
    }
    fun IncomingRequests()
    {
        var value:String=""
        myRef.child("Users")
                .child(SplitString(intent.extras.getString("Email")))
                .child("Request")
                .addValueEventListener(object:ValueEventListener{

                    override fun onDataChange(p0: DataSnapshot?) {

                        try {
                            val data = p0!!.value as HashMap<String, Any>
                            if (data != null) {
                                for (i in data.keys) {
                                    value = data[i] as String
                                    acceptName.text = value
                                    myRef.child("Users").child(SplitString(SplitString(intent.extras.getString("Email")))).child("Request").setValue(true)

                                    break;
                                }
                            }
                        }
                        catch (e:Exception)
                        {
                            Toast.makeText(this@Main2Activity,"Something went wrong",Toast.LENGTH_SHORT).show()}
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                })
    }
}
