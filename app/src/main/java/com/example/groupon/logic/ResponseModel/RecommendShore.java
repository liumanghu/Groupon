package com.example.groupon.logic.ResponseModel;

public class RecommendShore {
    private String shoreName;
    private String imgUrl;
    private float grade;
    private String shoreAddress;
    private float shoreConsume;
    private int sales;

    public RecommendShore(String shoreName, String imgUrl, float grade, String shoreAddress, float shoreConsume,int sales) {
        this.shoreName = shoreName;
        this.imgUrl = imgUrl;
        this.grade = grade;
        this.shoreAddress = shoreAddress;
        this.shoreConsume = shoreConsume;
        this.sales = sales;
    }

    public String getShoreName() {
        return shoreName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public float getGrade() {
        return grade;
    }

    public String getShoreAddress() {
        return shoreAddress;
    }

    public float getShoreConsume() {
        return shoreConsume;
    }

    public int getSales() {
        return sales;
    }
}
