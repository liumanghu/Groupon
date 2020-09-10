package com.example.groupon.UI.Activity.MainActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.groupon.MyApplication;
import com.example.groupon.R;
import com.example.groupon.UI.Adapter.BannerViewPagerAdapter;
import com.example.groupon.UI.Adapter.HomeBarAdapter;
import com.example.groupon.UI.Adapter.HotFilmListAdapter;
import com.example.groupon.UI.Adapter.HotSaleViewAdapter;
import com.example.groupon.UI.MyView.Indicator;
import com.example.groupon.UI.ViewModel.MainFragViewModel;
import com.example.groupon.logic.Network.MyHttpListener;
import com.example.groupon.logic.ResponseModel.FilmMessage;
import com.example.groupon.logic.ResponseModel.RecommendShore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

public class MainFragment extends Fragment implements MyHttpListener<Object> {

    private MainFragViewModel mViewModel;
    private Handler handler = new Handler();
    ViewPager bannerView;
    private boolean firstOpen = true;
    private static MainFragment mFragment;
    RecyclerView hotFilm, hotSaleListView;

    public static MainFragment newIntance(){
        if (mFragment == null){
            mFragment = new MainFragment();
        }
        return mFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainFragViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toolbar toolbar = getActivity().findViewById(R.id.mainFragToolbar);
        initToolbar(toolbar);
        //为广告条设置适配器
        initBannerView();
        //设置HomeBar
        initHomeBar();
        //获取LifeAdvertisingView的设置
        initLifeAdvertisingView();
        //获取hotFilmList
        initHotFilmListView();
        //获取hotSaleList
        initHotSaleListView();
    }

    /***********************************************************************************************************/
    /*
     *初始化猜你喜欢
     */

    private void initHotSaleListView() {
        hotSaleListView = getActivity().findViewById(R.id.HotSaleListView);
        mViewModel.getResponse(1,MyApplication.hotSaleUrl,this,true,true);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        hotSaleListView.setLayoutManager(layoutManager1);
    }

    /***********************************************************************************************************/
    /*
     *Toolbar相关设置
     */
    private void initToolbar(Toolbar toolbar) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mainfrag_toolbar_menu, menu);
    }

/***********************************************************************************************************/
/*
*广告条相关设置
 */
private void initBannerView(){
    bannerView = getActivity().findViewById(R.id.bannerView);
    Indicator BannerIndicator = getActivity().findViewById(R.id.indicator);
    bannerView.setAdapter(new BannerViewPagerAdapter(mViewModel.getImagArray()));
    //为广告条设置滚动监听
    bannerView.setOnPageChangeListener(new MyPageChangeListener(BannerIndicator));
    //控制广告条自动轮转
    if (firstOpen) {
        autoScoroll();
        firstOpen = false;
    }
}
    private void autoScoroll() {
        handler.postDelayed(runnable, 2000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = bannerView.getCurrentItem();
            if (currentItem == 2) {
                bannerView.setCurrentItem(0);
            } else {
                bannerView.setCurrentItem(currentItem + 1);
            }
            autoScoroll();
        }
    };

    /******************************************************************************************************************/
    //homeBar相关设置
    private void initHomeBar() {
        ViewPager homebar = getActivity().findViewById(R.id.HomeBar);
        homebar.setAdapter(new HomeBarAdapter(mViewModel.getHomeBarGridViewItem()));
        //获取HomebarIndicator对象
        Indicator homebarIndicator = getActivity().findViewById(R.id.HomeBarIndicator);
        homebar.setOnPageChangeListener(new MyPageChangeListener(homebarIndicator));
    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private Indicator indicator;

        MyPageChangeListener(Indicator indicator){
            this.indicator = indicator;
        }
        //viewPager滚动的

        /****
         *
         * @param position
         * @param positionOffset  page的偏移比例
         * @param positionOffsetPixels  page的偏移量
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            indicator.setPositionOffset(position, positionOffset);
        }

        //viewPager选中的
        @Override
        public void onPageSelected(int position) {

        }

        //ViewPager状态改变的
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    /******************************************************************************************************************/
    //lifeAdvertisingView的设置
    private void initLifeAdvertisingView(){
        GridView lifeAdvertisingView = getActivity().findViewById(R.id.LifeAdvertisingView);
        SimpleAdapter lifeAdvertisingViewAdapter = new SimpleAdapter(getActivity(),mViewModel.getLifeAdvertisingViewItems(),
                R.layout.lifeaverstingview_item,new String[]{"img"},new int[]{R.id.AdverstingViewItemImg});
        lifeAdvertisingView.setAdapter(lifeAdvertisingViewAdapter);
    }

    /******************************************************************************************************************/
    /**电影列表基本设置*/

    private void initHotFilmListView() {
        hotFilm = getActivity().findViewById(R.id.HotFilmList);
        mViewModel.getResponse(0, MyApplication.hotFilmUrl,this,true,true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        hotFilm.setLayoutManager(layoutManager);
    }

/******************************************************************************************************************/
    /**请求Http之后的回调*/
    Gson gson =new Gson();
    @Override
    public void onSucceed(int what, Response<Object> response) {
        switch (what){
            case 0:
                FilmMessage result = gson.fromJson(response.get().toString(),FilmMessage.class);
                List<FilmMessage.SubjectsEntity> subjects = result.getSubjects(); //获取影视列表
                hotFilm.setAdapter(new HotFilmListAdapter(subjects));
                break;
            case 1:
                List<RecommendShore> recommendShores = gson.fromJson(response.get().toString(),new TypeToken<List<RecommendShore>>(){}.getType());
                hotSaleListView.setAdapter(new HotSaleViewAdapter(recommendShores));
                break;
        }
    }

    @Override
    public void onFailed(int what, Response<Object> response) {

    }


/******************************************************************************************************************/
/**
 * 菜单点击事件监听器
 */
int REQUEST_CODE= 0;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.QR_code:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},1);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     *处理权限获取结果
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode== 1 ){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }else {
                Toast.makeText(getActivity(), "你拒绝了权限申请", Toast.LENGTH_SHORT).show();
            }
        }
    }

   /**
     * 处理二维码扫描结果
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

