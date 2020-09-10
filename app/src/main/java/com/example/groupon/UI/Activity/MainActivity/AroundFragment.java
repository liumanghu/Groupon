package com.example.groupon.UI.Activity.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupon.R;
import com.example.groupon.UI.ViewModel.AroundFragViewModel;
import com.example.groupon.logic.Network.MyHttpListener;
import com.yanzhenjie.nohttp.rest.Response;

public class AroundFragment extends Fragment implements MyHttpListener {

    private AroundFragViewModel mViewModel;
    private static AroundFragment aroundFragment;

    public static AroundFragment newInstance() {
        if (aroundFragment == null){
            aroundFragment = new AroundFragment();
        }
        return aroundFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.around_fragment, container, false);

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AroundFragViewModel.class);
        Toolbar toolbar = getActivity().findViewById(R.id.aroundFragToolbar);
        initToolbar(toolbar);



        // TODO: Use the ViewModel
    }

    private void initToolbar(Toolbar toolbar) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.aroundfrag_toolbar_menu,menu);
    }

    @Override
    public void onSucceed(int what, Response response) {

    }

    @Override
    public void onFailed(int what, Response response) {

    }
}