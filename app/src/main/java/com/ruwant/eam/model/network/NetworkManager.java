package com.ruwant.eam.model.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by 00265372 on 2017/2/9.
 */

public class NetworkManager {
    private static NetworkManager mInstance;
    private RequestQueue mRequestQueue;
//    private ImageLoader mImageLoader;
    private static Context mCtx;


    private NetworkManager(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkManager(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx, new OkHttp3Stack(new OkHttpClient()));
        }
        return mRequestQueue;
    }


    private <T> Request<T> add(Request<T> request) {

        return mRequestQueue.add(request);//添加请求到队列
    }

//    /**
//     * @param tag
//     * @param url
//     * @param listener
//     * @param errorListener
//     * @return
//     */
//    public StringRequest StrRequest(Object tag, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        StringRequest request = new StringRequest(url, listener, errorListener);
//        request.setTag(tag);
//        add(request);
//        return request;
//    }
//
//    /**
//     * @param tag
//     * @param method
//     * @param url
//     * @param listener
//     * @param errorListener
//     * @return
//     */
//    public StringRequest StrRequest(Object tag, int method, String url, Response.Listener<String> listener,
//                                    Response.ErrorListener errorListener) {
//        StringRequest request = new StringRequest(method, url, listener, errorListener);
//        request.setTag(tag);
//        add(request);
//        return request;
//    }
//
//    /**
//     * Get方法
//     *
//     * @param tag
//     * @param url
//     * @param clazz
//     * @param listener
//     * @param errorListener
//     * @param <T>
//     * @return
//     */
//    public <T> GsonRequest<T> GsonGetRequest(Object tag, String url, Class<T> clazz, Response.Listener<T> listener,
//                                             Response.ErrorListener errorListener) {
//        GsonRequest<T> request = new GsonRequest<T>(url, clazz, listener, errorListener);
//        request.setTag(tag);
//        add(request);
//        return request;
//    }
//
//    /**
//     * Post方式1：Map参数
//     *
//     * @param tag
//     * @param params
//     * @param url
//     * @param clazz
//     * @param listener
//     * @param errorListener
//     * @param <T>
//     * @return
//     */
//    public <T> GsonRequest<T> GsonPostRequest(Object tag, Map<String, String> params, String url,
//                                              Class<T> clazz, Response.Listener<T> listener,
//                                              Response.ErrorListener errorListener) {
//        GsonRequest<T> request = new GsonRequest<T>(Request.Method.POST, params, url, clazz, listener, errorListener);
//        request.setTag(tag);
//        add(request);
//        return request;
//    }

    /**
     * json字符串
     *
     * @param url
     * @param jsonObject
     * @param listener
     * @param errorListener
     */
    public void JsonRequest(Object tag, String url, JSONObject jsonObject, Response.Listener<JSONObject> listener,
                                Response.ErrorListener errorListener) {
        JsonRequest jsonRequest;
        jsonRequest = new JsonRequest(url, jsonObject, listener, errorListener);
        jsonRequest.setTag(tag);
        add(jsonRequest);

    }

    /**
     * 取消请求
     *
     * @param tag
     */
    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

//    public ImageLoader getImageLoader() {
//        return mImageLoader;
//    }
}

