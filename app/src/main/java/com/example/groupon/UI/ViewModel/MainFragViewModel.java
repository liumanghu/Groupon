package com.example.groupon.UI.ViewModel;

import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import com.example.groupon.MyApplication;
import com.example.groupon.R;
import com.example.groupon.UI.Adapter.HomeBarGridAdapter;
import com.example.groupon.logic.Entry.HomeItemInfo;
import com.example.groupon.logic.Network.MyHttpListener;
import com.example.groupon.logic.Repository.Repository;
import com.example.groupon.logic.ResponseModel.FilmMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //保存广告条数据
    private static int[] imags = new int[]{R.drawable.banner01, R.drawable.banner02, R.drawable.banner03};

    //保存MainFragment中的HomeBar的数据
    private static List<HomeItemInfo> homeItemInfoListPageOne = new ArrayList<>();
    private static List<HomeItemInfo> homeItemInfoListPageTwo = new ArrayList<>();
    private static List<View> homeBarGridViewItem = new ArrayList<>();
    public static List<View> imagArray = new ArrayList<>();

    //保存LifeAdvertisingView中的数据
    private static int[] advertisingViewImgs = new int[]{R.drawable.life_advertising01,R.drawable.life_advertising02,
            R.drawable.life_advertising03,R.drawable.life_advertising04};
    private static List<Map<String,Integer>> lifeAdvertisingViewItems = new ArrayList<>();

    //获取热门电影
    private FilmMessage hotFilmMessage;
    //仓库类
    private static Repository repository;

    static {
        //初始化仓库类
        repository = Repository.getInstance();
        //初始化bannerView的数据容器
        for (int i = 0; i < imags.length; i++) {
            LayoutInflater inflater = LayoutInflater.from(MyApplication.mContext);
            View view = inflater.inflate(R.layout.banner_viewpager_item, null);
            ImageView imageView = view.findViewById(R.id.bannerItemImage);
            imageView.setImageResource(imags[i]);
            imagArray.add(view);
        }

        //初始化MainFragment中的HomeBar的数据容器
        TypedArray typedArray = MyApplication.mContext.getResources().obtainTypedArray(R.array.home_bar_icon);
        String[] stringArray = MyApplication.mContext.getResources().getStringArray(R.array.home_bar_labels);
        for (int i = 0; i < stringArray.length; i++) {
            if (i < 8) {
                homeItemInfoListPageOne.add(new HomeItemInfo(typedArray.getResourceId(i,0), stringArray[i]));
            } else {
                homeItemInfoListPageTwo.add(new HomeItemInfo(typedArray.getResourceId(i,0), stringArray[i]));
            }
        }
        //初始化HomeBar的数据
        View view = LayoutInflater.from(MyApplication.mContext).inflate(R.layout.homebar_gridview,null);
        GridView gridView = view.findViewById(R.id.HomeBarGridView);
        gridView.setAdapter(new HomeBarGridAdapter(homeItemInfoListPageOne));

        View view2 = LayoutInflater.from(MyApplication.mContext).inflate(R.layout.homebar_gridview,null);
        GridView gridView2 = view2.findViewById(R.id.HomeBarGridView);
        gridView2.setAdapter(new HomeBarGridAdapter(homeItemInfoListPageTwo));

        homeBarGridViewItem.add(view);
        homeBarGridViewItem.add(view2);

        //初始化LifeAdvertisingView的数据
        for (int i = 0; i < advertisingViewImgs.length; i++) {
            Map<String,Integer> map = new HashMap<>();
            map.put("img",advertisingViewImgs[i]);
            lifeAdvertisingViewItems.add(map);
        }
    }

    public List<View> getImagArray() {
        return imagArray;
    }

    public List<View> getHomeBarGridViewItem() {
        return homeBarGridViewItem;
    }

    public List<Map<String, Integer>> getLifeAdvertisingViewItems() {
        return lifeAdvertisingViewItems;
    }



    /*******************************************************************/
    /**
     * 获取热门电影
     */
    public <T> void getResponse(int what,String url, MyHttpListener<T> myHttpListener, boolean isCancelable, boolean isLoding){
        repository.getResponse(what,url,myHttpListener,isCancelable,isLoding);
    }
}