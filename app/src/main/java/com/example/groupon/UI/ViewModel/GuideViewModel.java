package com.example.groupon.UI.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import com.example.groupon.MyApplication;
import com.example.groupon.R;

import java.util.ArrayList;
import java.util.List;

public class GuideViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private static int[] imas = new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3,R.mipmap.guide_4};

    private static List<View> imasList = new ArrayList<>();
    //初始化guide界面的viewpager的imagelist
    static {
        for (int i = 0; i < imas.length; i++) {
            LayoutInflater inflater = LayoutInflater.from(MyApplication.mContext);
            View view = inflater.inflate(R.layout.guide_viewpager_item,null);
            ImageView imageView = view.findViewById(R.id.guideImageView);
            imageView.setImageResource(imas[i]);
            imasList.add(view);
        }
    }

    public List<View> getImasList() {
        return imasList;
    }
}