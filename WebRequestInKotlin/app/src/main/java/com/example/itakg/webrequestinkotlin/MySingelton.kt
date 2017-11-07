package com.example.itakg.webrequestinkotlin

/**
 * Created by itakg on 9/17/2017.
 */

import android.content.Context
import com.android.volley.R

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingelton private constructor(context: Context) {
    private var requestQueue: RequestQueue? = null


    init {
        mCtx = context
        requestQueue = getRequestQueue()
    }


    fun getRequestQueue(): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.applicationContext)
        }
        return requestQueue as RequestQueue
    }


    fun addToRequestQue(request: Request<*>) {
        requestQueue!!.add(request)
    }

    companion object {


        private var mInstance: MySingelton? = null
        private lateinit var mCtx: Context


        @Synchronized fun getInstance(context: Context): MySingelton {
            if (mInstance == null) {
                mInstance = MySingelton(context)
            }
            return mInstance as MySingelton
        }
    }
}


