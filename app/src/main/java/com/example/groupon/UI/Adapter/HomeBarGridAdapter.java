package com.example.groupon.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.groupon.MyApplication;
import com.example.groupon.R;
import com.example.groupon.logic.Entry.HomeItemInfo;

import java.util.List;

public class HomeBarGridAdapter extends BaseAdapter {
    private List<HomeItemInfo> gridViewItemDatas;

    public HomeBarGridAdapter(List<HomeItemInfo> homeItemInfos){
        this.gridViewItemDatas = homeItemInfos;
    }

    @Override
    public int getCount() {
        return gridViewItemDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return gridViewItemDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(MyApplication.mContext).inflate(R.layout.homebar_gridview_item, null);
        ImageView imageView = inflate.findViewById(R.id.HomeBarGridViewItemImage);
        imageView.setImageResource(gridViewItemDatas.get(i).getHomeImgId());
        TextView textView = inflate.findViewById(R.id.HomebarGrdieViewLable);
        textView.setText(gridViewItemDatas.get(i).getHomeLable());
        return inflate;
    }
}
