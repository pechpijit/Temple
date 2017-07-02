package com.sdu.tample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProvince {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("provinceName")
    @Expose
    private String provinceName;
    @SerializedName("provinceImage")
    @Expose
    private String provinceImage;
    @SerializedName("provinceDetail")
    @Expose
    private Object provinceDetail;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceImage() {
        return provinceImage;
    }

    public void setProvinceImage(String provinceImage) {
        this.provinceImage = provinceImage;
    }

    public Object getProvinceDetail() {
        return provinceDetail;
    }

    public void setProvinceDetail(Object provinceDetail) {
        this.provinceDetail = provinceDetail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
