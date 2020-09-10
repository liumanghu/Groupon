package com.example.groupon.UI.Activity.SplashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.groupon.R;
import com.example.groupon.UI.Activity.MainActivity.MainActivity;
import com.example.groupon.UI.Adapter.GuideViewPagerAdapter;
import com.example.groupon.UI.ViewModel.GuideViewModel;

/***
 * 引导界面
 */
public class GuideFragment extends Fragment {

    private GuideViewModel mViewModel;
    private static GuideFragment guideFragment;

    public static GuideFragment newInstance() {
        if (guideFragment == null){
            guideFragment = new GuideFragment();
        }
        return guideFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guide_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GuideViewModel.class);
        // TODO: Use the ViewModel
        //设置guide界面的viewpager
        ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
        final Button start_btn = getActivity().findViewById(R.id.start_btn);
        //为按键的出现设置动画
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_guide_btn_start);
        start_btn.startAnimation(animation);
        //为button设置监听事件
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        GuideViewPagerAdapter adapter = new GuideViewPagerAdapter(mViewModel.getImasList());
        viewPager.setAdapter(adapter);
        //为viewpager设置监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mViewModel.getImasList().size()-1){
                    start_btn.setVisibility(View.VISIBLE);
                }else {
                    start_btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



}