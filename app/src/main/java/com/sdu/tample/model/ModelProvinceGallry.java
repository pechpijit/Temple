package com.sdu.tample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProvinceGallry {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("galleryImageName")
    @Expose
    private String galleryImageName;
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

    public String getGalleryImageName() {
        return galleryImageName;
    }

    public void setGalleryImageName(String galleryImageName) {
        this.galleryImageName = galleryImageName;
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
