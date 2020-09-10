package com.example.groupon.UI.Activity.MainActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.groupon.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentView, MainFragment.newIntance());
        transaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(l);
    }

    BottomNavigationView.OnNavigationItemSelectedListener l = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.bottom_home:
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                    fragment = MainFragment.newIntance();
                    break;
                case R.id.bottom_tab_near:
                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                    fragment = AroundFragment.newInstance();
                    break;
                case R.id.bottom_tab_my:
                    bottomNavigationView.getMenu().getItem(2).setChecked(true);
                    fragment = MyFragment.newInstance();
                    break;
                case R.id.bottom_tab_more:
                    bottomNavigationView.getMenu().getItem(3).setChecked(true);
                    fragment = MoreFragment.newInstance();
                    break;
            }
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentView,fragment);
            transaction.commit();
            return false;
        }
    };
}