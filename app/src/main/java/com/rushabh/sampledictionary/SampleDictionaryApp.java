package com.rushabh.sampledictionary;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by rushabh on 22/04/16.
 */
public class SampleDictionaryApp extends Application {

    RequestQueue requestQueue;
    private static SampleDictionaryApp mInstance;

    public static final String TAG = SampleDictionaryApp.class
            .getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized SampleDictionaryApp getInstance(){
        return mInstance;
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            HurlStack stack = new HurlStack();
            requestQueue = Volley.newRequestQueue(this, stack);
        }

        return requestQueue;
    }

    public void addRequest(StringRequest request, String tag) {
        request.setTag(tag == null ? TAG : tag);
        getRequestQueue().add(request);
    }
}
