package com.example.itakg.onlinerequest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var dbRef=FirebaseDatabase.getInstance()
    var myRef=dbRef.reference

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

    }

    public fun clicked(view: View) {
        mAuth!!.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).
                addOnCompleteListener(this) {
                    task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Successful login", Toast.LENGTH_LONG).show()
                        myRef.child("Users").child(SplitString(email.text.toString())).child("Request").setValue(mAuth!!.currentUser!!.uid.toString())

                    LoadMain()

                    } else {

                        Toast.makeText(applicationContext, "Failed login", Toast.LENGTH_LONG).show()
                    }


                }



    }

    override  fun onStart() {
        super.onStart()
        LoadMain()
    }

    fun LoadMain()
    {
        if(mAuth!!.currentUser!=null)
        {
            var intent:Intent= Intent(this,Main2Activity::class.java)
            intent.putExtra("Email",email.text.toString())
            intent.putExtra("uid",email.text.toString())
            startActivity(intent)
            finish()
        }
    }





    fun  SplitString(str:String):String{
        var split=str.split("@")
        return split[0]
    }
}
