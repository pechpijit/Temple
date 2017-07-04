package com.sdu.tample.model;

import java.util.ArrayList;

/**
 * Created by Developer on 4/7/2560.
 */
public class ModelProvinceNew {
    ModelProvince province;
    ArrayList<ModelProvinceGallry> gallries;

    public ModelProvince getProvince() {
        return province;
    }

    public void setProvince(ModelProvince province) {
        this.province = province;
    }

    public ArrayList<ModelProvinceGallry> getGallries() {
        return gallries;
    }

    public void setGallries(ArrayList<ModelProvinceGallry> gallries) {
        this.gallries = gallries;
    }
}
