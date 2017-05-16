package com.sdu.tample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelHot {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("interestingName")
    @Expose
    private String interestingName;
    @SerializedName("interestingDetail")
    @Expose
    private String interestingDetail;
    @SerializedName("interestingLatitude")
    @Expose
    private String interestingLatitude;
    @SerializedName("interestingLongitude")
    @Expose
    private String interestingLongitude;
    @SerializedName("interestingStatus")
    @Expose
    private String interestingStatus;
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

    public String getInterestingName() {
        return interestingName;
    }

    public void setInterestingName(String interestingName) {
        this.interestingName = interestingName;
    }

    public String getInterestingDetail() {
        return interestingDetail;
    }

    public void setInterestingDetail(String interestingDetail) {
        this.interestingDetail = interestingDetail;
    }

    public String getInterestingLatitude() {
        return interestingLatitude;
    }

    public void setInterestingLatitude(String interestingLatitude) {
        this.interestingLatitude = interestingLatitude;
    }

    public String getInterestingLongitude() {
        return interestingLongitude;
    }

    public void setInterestingLongitude(String interestingLongitude) {
        this.interestingLongitude = interestingLongitude;
    }

    public String getInterestingStatus() {
        return interestingStatus;
    }

    public void setInterestingStatus(String interestingStatus) {
        this.interestingStatus = interestingStatus;
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
