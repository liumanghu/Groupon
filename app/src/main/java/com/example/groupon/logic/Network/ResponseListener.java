package com.example.groupon.logic.Network;

import android.content.Context;
import android.content.DialogInterface;

import com.example.groupon.UI.MyView.WaitDialog;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 *自定义ResponseListener
 */
public class ResponseListener<T> implements OnResponseListener<T> {

    //自己定义的监听器
    private MyHttpListener listener;
    //自己定义的对话框
    WaitDialog waitDialog;
    private boolean isLoading;

    public ResponseListener(Context mContext,MyHttpListener listener, boolean isCancelable, boolean isLoding) {
        this.listener = listener;
        this.isLoading = isLoding;
        waitDialog = new WaitDialog(mContext);
        waitDialog.setCancelable(isCancelable);
        waitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                waitDialog.cancel();
            }
        });
    }

    @Override
    public void onStart(int what) {
//        if (waitDialog != null && isLoading && !waitDialog.isShowing()){
//            waitDialog.show();
//        }
    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        if (listener != null){
            listener.onSucceed(what,response);
        }

    }

    @Override
    public void onFailed(int what, Response<T> response) {
        if (listener != null) {
            listener.onFailed(what,response);
        }

    }

    @Override
    public void onFinish(int what) {
//        if (waitDialog != null && isLoading && waitDialog.isShowing()){
//            waitDialog.cancel();
//        }
    }
}
