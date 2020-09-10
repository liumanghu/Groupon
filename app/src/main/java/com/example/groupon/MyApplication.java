package com.example.groupon;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.yanzhenjie.nohttp.NoHttp;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    //热门电影URL
    public static String hotFilmUrl = "http://t.yushu.im/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&count=12&city=上海&start=0";
    //猜你喜欢URL
    public static String hotSaleUrl = "http://10.0.2.2:8080/GrouponServer/v1.SpRecommend";
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //初始化NoHttp
        NoHttp.initialize(this);
        //初始化Fresco
        Fresco.initialize(this);
        //初始化二维码扫描模块
        ZXingLibrary.initDisplayOpinion(this);
    }
}
