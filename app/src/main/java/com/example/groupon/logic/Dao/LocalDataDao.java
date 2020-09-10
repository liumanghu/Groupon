package com.example.groupon.logic.Dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.groupon.MyApplication;

//用来查看本地数据的Dao
public class LocalDataDao {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static LocalDataDao dataDao;

    private LocalDataDao(){
        preferences = MyApplication.mContext.getSharedPreferences("Groupon", Context.MODE_PRIVATE);
    }

    public static LocalDataDao getInstance(){
        if (dataDao == null){
            dataDao = new LocalDataDao();
        }
        return dataDao;
    }
    //从shardpreferences的isFirst中判断是否是首次打开APP
    public boolean isFirstOpen(){
        return preferences.getBoolean("isFirst",true);
    }
    //设置isFirst
    public void setFirstOpen(boolean isFirst){
        editor = preferences.edit();
        editor.putBoolean("isFirst",isFirst);
        editor.apply();
    }
}
