package com.example.groupon.UI.Activity.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupon.R;
import com.example.groupon.UI.ViewModel.MoreFragViewModel;

public class MoreFragment extends Fragment {

    private MoreFragViewModel mViewModel;
    private static MoreFragment moreFragment;

    public static MoreFragment newInstance() {
        if (moreFragment == null){
            moreFragment = new MoreFragment();
        }
        return moreFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.more_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MoreFragViewModel.class);
        Toolbar toolbar = getActivity().findViewById(R.id.mroeFragToolbar);
        initToolbar(toolbar);
        // TODO: Use the ViewModel
    }

    private void initToolbar(Toolbar toolbar) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}