package com.example.groupon.logic.Repository;


import com.example.groupon.MyApplication;
import com.example.groupon.logic.Dao.LocalDataDao;
import com.example.groupon.logic.Network.CallServer;
import com.example.groupon.logic.Network.MyHttpListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

public class Repository {
    private LocalDataDao dataDao;
    CallServer callServer;

    private static Repository repository;

    public static Repository getInstance() {
        if (repository == null){
            repository = new Repository();
        }
        return repository;
    }
/*******************************************************************/
    private Repository(){
        dataDao = LocalDataDao.getInstance();
        callServer = CallServer.getInstance();
    }

    public boolean isFirst(){
        return dataDao.isFirstOpen();
    }

    public void setFirstOpen(boolean isFirst){
        dataDao.setFirstOpen(isFirst);
    }

/*******************************************************************/
    /**
     * 获取热门电影
     */
    public <T> void getResponse(int what, String url, MyHttpListener myHttpListener, boolean isCancleable, boolean isLoading){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        callServer.add(MyApplication.mContext, what, request,myHttpListener,isCancleable,isLoading);
    }
}
