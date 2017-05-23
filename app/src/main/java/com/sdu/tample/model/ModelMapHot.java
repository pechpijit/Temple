package com.sdu.tample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelMapHot {
    @SerializedName("wat")
    @Expose
    private ArrayList<ModelTempleMap> wat;
    @SerializedName("hot")
    @Expose
    private ArrayList<ModelHot> hot;

    public ArrayList<ModelTempleMap> getWat() {
        return wat;
    }

    public void setWat(ArrayList<ModelTempleMap> wat) {
        this.wat = wat;
    }

    public ArrayList<ModelHot> getHot() {
        return hot;
    }

    public void setHot(ArrayList<ModelHot> hot) {
        this.hot = hot;
    }
}
