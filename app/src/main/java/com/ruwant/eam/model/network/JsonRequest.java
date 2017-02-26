package com.ruwant.eam.model.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 00265372 on 2017/2/14.
 */

public class JsonRequest extends JsonObjectRequest {

    public JsonRequest(String url, JSONObject jsonRequest,
                       Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    public JsonRequest(int method, String url, JSONObject jsonRequest,
                       Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    //重写头信息，为了服务器授权
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("AppKey", "Android");

//        //如果已经登录，追加头信息
//        if(LoginHelper.isLogin())
//        {
//            headers.put(Config.HEADER_LOGIN_KEY, MyApplication.gUserID+","+MyApplication.gUserToken);
//        }

        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));

            Map<String, String> headers = response.headers;
            String cookies = headers.get("Set-Cookie");

            Log.v("login", "cookie:" + cookies);

            // 解析服务器返回的cookie值
            String sessionId = parseVooleyCookie(cookies);

            Log.v("login", "session_id:" + sessionId);
            // 存储cookie
//            SharedPreferenceUtil.putString(LoginActivity.this,
//                    "cookie", cookie);
            // 下面这句是把返回的数据传到onResponse回调里

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    /*
 * 方法的作用: 解析volley返回cookie
 */
    public String parseVooleyCookie(String cookie) {
        StringBuilder sb = new StringBuilder();
        String[] cookievalues = cookie.split(";");
        for (int j = 0; j < cookievalues.length; j++) {
            String[] keyPair = cookievalues[j].split("/");
            for (int i = 0; i < keyPair.length; i++) {
                if (keyPair[0].contains("session_id")) {
                    sb.append(keyPair[0]);
                    sb.append(";");
                    break;
                }

//                if (keyPair.length == 1) {
//                    if (keyPair[0].contains("session_id")) {
//                        sb.append(keyPair[0]);
//                        sb.append(";");
//                        break;
//                    }
//                } else {
//                    if (keyPair[0].contains("session_id")) {
//                        sb.append(keyPair[0]);
//                        sb.append(";");
//                        break;
//                    }
//
//                    if (keyPair[1].contains("Expires")) {
//                        sb.append(keyPair[1]);
//                        sb.append(";");
//                        break;
//                    }
//                }
            }
        }
        return sb.toString();
    }

    private void setMyRetryPolicy() {
        setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
