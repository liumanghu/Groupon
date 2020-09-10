package com.example.groupon.logic.Entry;

/**
 * 用来标示MainFragment中的HomeBar单个子项的Entry
 */
public class HomeItemInfo {
    private int homeImgId;
    private String homeLable;

    public HomeItemInfo(int homeImgId, String homeLable) {
        this.homeImgId = homeImgId;
        this.homeLable = homeLable;
    }

    public int getHomeImgId() {
        return homeImgId;
    }

    public void setHomeImgId(int homeImgId) {
        this.homeImgId = homeImgId;
    }

    public String getHomeLable() {
        return homeLable;
    }

    public void setHomeLable(String homeLable) {
        this.homeLable = homeLable;
    }
}
