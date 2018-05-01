package mx.itesm.csf.calvin_catalogue.Controllers;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

/**
 * Created by rodo on 01/05/2018.
 */


public class Controller extends Application
{

    private static final String TAG = Controller.class.getSimpleName();
    private static Controller instance  ;
    RequestQueue reqQueue;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    public static synchronized Controller getInstance()
            {
                return instance;
            }


   // To obtain volley request
        private RequestQueue getRequestQueue()
        {
            if(reqQueue == null)
            {
                reqQueue = Volley.newRequestQueue(getApplicationContext());
            }
            return reqQueue;
        }

    // To add to the Request Queue
        public <T> void addToRequestQueue(Request<T> request, String tag)
        {
            request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            VolleyLog.e("Adding to the request queue: %s", request.getUrl());
            getRequestQueue().add(request);
        }

    // Create Volley Petitions
        public <T> void addToRequestQueue (Request<T> req)
        {
            req.setTag(TAG);
            getRequestQueue().add(req);
        }

    // Cancell Volley Request
        public void cancelRequests(Object request)
        {
            if (reqQueue != null)
            {
                reqQueue.cancelAll(request);
            }
        }

}


