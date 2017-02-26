package com.ruwant.eam.model.network;

import android.util.Log;

import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.ruwant.eam.EamApplication;
import com.ruwant.eam.config.Urls;
import com.ruwant.eam.model.entity.UserInfo;
import com.ruwant.eam.presenter.EamNetworkPresenterListener;
import com.ruwant.eam.presenter.EamPresenter;
import com.ruwant.eam.util.EamLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkDataImpl implements NetworkData {

    final String TAG = "login";
    String baseUrl = "http://b2bdemo01.hollywant.com";

    private static Gson mGson = new Gson();
//    private Class<T> mClass;

    public void login(String userName, String password, final EamNetworkPresenterListener listener){



        /*post方式一：post map参数*/
//        Map<String, String> map = new HashMap<String, String>();
//        Map<String, String> parammap = new HashMap<String, String>();
//
//        map.put("jsonrpc", "2.0");
//        map.put("method", "call");
//
//
//        parammap.put("app_version", "0.5.0");
//        parammap.put("login", userName);
//        parammap.put("password", password);
//
//        map.put("params", parammap);

        /*post方式二：post json对象*/
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("jsonrpc", "2.0");
            jsonObject.put("method", "call");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject params = new JSONObject();

        try {
            params.put("app_version", "0.5.0");
            params.put("login", userName);
            params.put("password", password);

            jsonObject.put("params", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        request(Urls.LOGIN, jsonObject, UserInfo.class);
    }

    public void updateVersion(){

    }

    private void request(String url, JSONObject jsonObject, final Class<?> type){
        NetworkManager.getInstance(EamApplication.getContext()).JsonRequest(TAG, baseUrl + url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.v(TAG, "response json对象: " + jsonObject.toString());

                        parseToResponse(jsonObject, type);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
    }

    private Class<?> parseToResponse(JSONObject response, Class<?> type) {
        try {
            // Fixed for direct array in result
            // It will add one more key to result:(result:[] become result:{"result": []});
            if (response.has("result")) {
                if (!(response.get("result") instanceof JSONObject)) {
                    JSONObject obj = new JSONObject();
                    obj.put("result", response.get("result"));
                    response.put("result", obj);
                }
            } else if (!response.has("error")) {
                JSONObject result = response;
                response = new JSONObject();
                response.put("result", result);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return mGson.fromJson(response.toString(), type.getClass());
    }
}

