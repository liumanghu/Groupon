package com.example.groupon.logic.Network;

import android.content.Context;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

//工具类，实现对queue以及add方法的封装
public class CallServer {
    private static CallServer mCallServer;
    RequestQueue queue;

    private CallServer(){
        queue = NoHttp.newRequestQueue();
    }

    public synchronized static CallServer getInstance(){
        if (mCallServer == null){
            mCallServer = new CallServer();
        }
        return mCallServer;
    }

    public <T> void add(Context context,int what, Request<T> request, MyHttpListener myHttpListener, boolean isCancleable, boolean isLoading){
        queue.add(what,request,new ResponseListener<T>(context,myHttpListener,isCancleable,isLoading));
    }


}
