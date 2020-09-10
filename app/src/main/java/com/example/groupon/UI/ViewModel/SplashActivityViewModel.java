package com.example.groupon.UI.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.groupon.logic.Repository.Repository;

public class SplashActivityViewModel extends ViewModel {
    private Repository repository = Repository.getInstance();

    public boolean isFirstOpen(){
        return repository.isFirst();
    }

    public void setFirstOpen(boolean isFirst){
        repository.setFirstOpen(isFirst);
    }
}
