package com.sdu.tample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelVehicle {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vehicleName")
    @Expose
    private String vehicleName;
    @SerializedName("vehicleAddress")
    @Expose
    private String vehicleAddress;
    @SerializedName("vehicleTel1")
    @Expose
    private String vehicleTel1;
    @SerializedName("vehicleTel2")
    @Expose
    private String vehicleTel2;
    @SerializedName("vehiclePirceMin")
    @Expose
    private Integer vehiclePirceMin;
    @SerializedName("vehiclePirceMax")
    @Expose
    private Integer vehiclePirceMax;
    @SerializedName("vehicleDetail")
    @Expose
    private String vehicleDetail;
    @SerializedName("vehicleCategory")
    @Expose
    private Integer vehicleCategory;
    @SerializedName("vehicleLatitude")
    @Expose
    private String vehicleLatitude;
    @SerializedName("vehicleLongitude")
    @Expose
    private String vehicleLongitude;
    @SerializedName("vehicleImage")
    @Expose
    private String vehicleImage;
    @SerializedName("vehicleTimeOpen")
    @Expose
    private String vehicleTimeOpen;
    @SerializedName("vehicleTimeClose")
    @Expose
    private String vehicleTimeClose;
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

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleAddress() {
        return vehicleAddress;
    }

    public void setVehicleAddress(String vehicleAddress) {
        this.vehicleAddress = vehicleAddress;
    }

    public String getVehicleTel1() {
        return vehicleTel1;
    }

    public void setVehicleTel1(String vehicleTel1) {
        this.vehicleTel1 = vehicleTel1;
    }

    public String getVehicleTel2() {
        return vehicleTel2;
    }

    public void setVehicleTel2(String vehicleTel2) {
        this.vehicleTel2 = vehicleTel2;
    }

    public Integer getVehiclePirceMin() {
        return vehiclePirceMin;
    }

    public void setVehiclePirceMin(Integer vehiclePirceMin) {
        this.vehiclePirceMin = vehiclePirceMin;
    }

    public Integer getVehiclePirceMax() {
        return vehiclePirceMax;
    }

    public void setVehiclePirceMax(Integer vehiclePirceMax) {
        this.vehiclePirceMax = vehiclePirceMax;
    }

    public String getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(String vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    public Integer getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(Integer vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getVehicleLatitude() {
        return vehicleLatitude;
    }

    public void setVehicleLatitude(String vehicleLatitude) {
        this.vehicleLatitude = vehicleLatitude;
    }

    public String getVehicleLongitude() {
        return vehicleLongitude;
    }

    public void setVehicleLongitude(String vehicleLongitude) {
        this.vehicleLongitude = vehicleLongitude;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getVehicleTimeOpen() {
        return vehicleTimeOpen;
    }

    public void setVehicleTimeOpen(String vehicleTimeOpen) {
        this.vehicleTimeOpen = vehicleTimeOpen;
    }

    public String getVehicleTimeClose() {
        return vehicleTimeClose;
    }

    public void setVehicleTimeClose(String vehicleTimeClose) {
        this.vehicleTimeClose = vehicleTimeClose;
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