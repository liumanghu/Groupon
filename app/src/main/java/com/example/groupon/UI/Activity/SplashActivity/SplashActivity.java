package com.example.groupon.UI.Activity.SplashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupon.UI.Activity.MainActivity.MainActivity;
import com.example.groupon.R;
import com.example.groupon.UI.ViewModel.SplashActivityViewModel;

public class SplashActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private SplashActivityViewModel viewModel;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        fragmentManager = getSupportFragmentManager();

        viewModel = new ViewModelProvider(this).get(SplashActivityViewModel.class);

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewModel.isFirstOpen()){
                    FrameLayout layout = findViewById(R.id.splashPage);
                    layout.removeAllViews();
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.splashPage,GuideFragment.newInstance());
                    transaction.commitNow();
                    viewModel.setFirstOpen(false);
                }else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }
}