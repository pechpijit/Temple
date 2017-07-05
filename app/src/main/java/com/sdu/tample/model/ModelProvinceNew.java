package com.sdu.tample.model;

import java.util.ArrayList;

public class ModelProvinceNew {
    ModelProvince province;
    ArrayList<ModelProvinceGallry> image;

    public ModelProvince getProvince() {
        return province;
    }

    public void setProvince(ModelProvince province) {
        this.province = province;
    }

    public ArrayList<ModelProvinceGallry> getImage() {
        return image;
    }

    public void setImage(ArrayList<ModelProvinceGallry> image) {
        this.image = image;
    }
}
