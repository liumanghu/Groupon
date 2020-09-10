package com.example.groupon.UI.Activity.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupon.R;
import com.example.groupon.UI.ViewModel.MyFragViewModel;

public class MyFragment extends Fragment{

    private MyFragViewModel mViewModel;
    private static MyFragment myFragment;
    TextView textView;

    public static MyFragment newInstance() {
        if (myFragment == null){
            myFragment = new MyFragment();
        }
        return myFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyFragViewModel.class);
        Toolbar toolbar = getActivity().findViewById(R.id.myFragToolbal);
        initToolbar(toolbar);
        // TODO: Use the ViewModel
    }

    private void initToolbar(Toolbar toolbar) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}