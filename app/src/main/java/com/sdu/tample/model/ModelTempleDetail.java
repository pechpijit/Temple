package com.sdu.tample.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelTempleDetail {

    @SerializedName("temple")
    @Expose
    private ModelTemple temple;
    @SerializedName("gallery")
    @Expose
    private ArrayList<ModelGallery> gallery;

    public ModelTemple getTemple() {
        return temple;
    }

    public void setTemple(ModelTemple temple) {
        this.temple = temple;
    }

    public ArrayList<ModelGallery> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<ModelGallery> gallery) {
        this.gallery = gallery;
    }
}