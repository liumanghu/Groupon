package com.example.groupon.UI.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

/***
 * Guide界面的viewPager的适配器
 */
public class BannerViewPagerAdapter extends PagerAdapter {
    private List<View> imgs;

    public BannerViewPagerAdapter(List<View> imgs) {
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = imgs.get(position);

        //判断其父容器是否存在，如存在，先和此子控件解除关系
        ViewPager parent = (ViewPager) view.getParent();
        if (parent != null){
            parent.removeView(view);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView(imgs.get(position));
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
