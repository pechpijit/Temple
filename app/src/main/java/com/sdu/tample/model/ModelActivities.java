package com.sdu.tample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelActivities {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("activitiesName")
    @Expose
    private String activitiesName;
    @SerializedName("activitiesDetail")
    @Expose
    private String activitiesDetail;
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

    public String getActivitiesName() {
        return activitiesName;
    }

    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName;
    }

    public String getActivitiesDetail() {
        return activitiesDetail;
    }

    public void setActivitiesDetail(String activitiesDetail) {
        this.activitiesDetail = activitiesDetail;
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
}