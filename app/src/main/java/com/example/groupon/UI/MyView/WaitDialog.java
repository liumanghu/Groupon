package com.example.groupon.UI.MyView;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

public class WaitDialog extends ProgressDialog {

    public WaitDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage("正在请求，请稍后...");
    }

    public WaitDialog(Context context, int theme) {
        super(context, theme);
    }
}
