package com.sdu.tample.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelImageSlide {

    @SerializedName("templeName")
    @Expose
    private String templeName;
    @SerializedName("templeImage")
    @Expose
    private String templeImage;

    public String getTempleName() {
        return templeName;
    }

    public void setTempleName(String templeName) {
        this.templeName = templeName;
    }

    public String getTempleImage() {
        return templeImage;
    }

    public void setTempleImage(String templeImage) {
        this.templeImage = templeImage;
    }

}