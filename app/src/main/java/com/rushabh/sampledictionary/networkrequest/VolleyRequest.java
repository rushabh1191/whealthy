package com.rushabh.sampledictionary.networkrequest;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rushabh.sampledictionary.helper.Logger;
import com.rushabh.sampledictionary.SampleDictionaryApp;

import org.json.JSONException;
import org.json.JSONObject;


public class VolleyRequest {


    public static String TAG = "volley";


    private VolleyResponseListener onVolleyResponse;

    public VolleyRequest(  final int requestId,  final VolleyResponseListener listener) {

        SampleDictionaryApp app = SampleDictionaryApp.getInstance();

        StringRequest volleyRequest = createRequest(requestId,listener);
        app.addRequest(volleyRequest, requestId + "");

    }

    public VolleyRequest() {

    }

    public StringRequest createRequest( final int requestId, final VolleyResponseListener listener) {

        onVolleyResponse = listener;

        String finalUrl = "http://appsculture.com/vocab/words.json";


        StringRequest request = new StringRequest(Request.Method.GET, finalUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (onVolleyResponse != null) {

                    try {

                        Logger.log("beta","re" +response);
                        JSONObject jsonObject = new JSONObject(response);
                        onVolleyResponse.responseRecieved(jsonObject, requestId);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        Logger.log("beta","re" +error.getMessage());
                        if (onVolleyResponse != null)
                            onVolleyResponse.errorRecieved(error, requestId);
                    }
                });


        request.setShouldCache(false);

        int socketTimeout = 30000;
        request.setRetryPolicy(new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return request;
    }
}
