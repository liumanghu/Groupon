package com.example.groupon.logic.Network;

import com.yanzhenjie.nohttp.rest.Response;

/**
 *创建自定义接口，实现对onSucceed和onFailed方法的封装
 */
public interface MyHttpListener<T> {
    void onSucceed(int what, Response<T> response);

    void onFailed(int what, Response<T> response);
}
