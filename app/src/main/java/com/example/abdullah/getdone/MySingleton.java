package com.example.abdullah.getdone;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
        private static com.example.abdullah.getdone.MySingleton mInstance;
        private RequestQueue requestQueue;
        private static Context mCTX;

        private MySingleton(Context context)
        {
            mCTX = context;
            requestQueue = getRequestQueue();
        }
        public static synchronized com.example.abdullah.getdone.MySingleton getInstance(Context context)
        {
            if(mInstance==null)
            {
                mInstance = new com.example.abdullah.getdone.MySingleton(context);
            }
            return mInstance;
        }

        public RequestQueue getRequestQueue()
        {
            if(requestQueue==null)
            {
                requestQueue = Volley.newRequestQueue(mCTX.getApplicationContext());
            }
            return requestQueue;
        }

        public <T>void addTorequestquee(Request<T> request)
        {
            requestQueue.add(request);
        }
    }

