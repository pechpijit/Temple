package com.sdu.tample.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTemple {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("templeName")
    @Expose
    private String templeName;
    @SerializedName("templeDetail")
    @Expose
    private String templeDetail;
    @SerializedName("templeHolyObject")
    @Expose
    private String templeHolyObject;
    @SerializedName("templeTimeOpen")
    @Expose
    private String templeTimeOpen;
    @SerializedName("templeTimeClose")
    @Expose
    private String templeTimeClose;
    @SerializedName("templeAddress")
    @Expose
    private String templeAddress;
    @SerializedName("templeSubDistrictArea")
    @Expose
    private String templeSubDistrictArea;
    @SerializedName("templeDistrictArea")
    @Expose
    private String templeDistrictArea;
    @SerializedName("templeProvince")
    @Expose
    private String templeProvince;
    @SerializedName("templePostalCode")
    @Expose
    private Integer templePostalCode;
    @SerializedName("templeLatittude")
    @Expose
    private String templeLatittude;
    @SerializedName("templeLongitude")
    @Expose
    private String templeLongitude;
    @SerializedName("templeImage")
    @Expose
    private String templeImage;
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

    public String getTempleName() {
        return templeName;
    }

    public void setTempleName(String templeName) {
        this.templeName = templeName;
    }

    public String getTempleDetail() {
        return templeDetail;
    }

    public void setTempleDetail(String templeDetail) {
        this.templeDetail = templeDetail;
    }

    public String getTempleHolyObject() {
        return templeHolyObject;
    }

    public void setTempleHolyObject(String templeHolyObject) {
        this.templeHolyObject = templeHolyObject;
    }

    public String getTempleTimeOpen() {
        return templeTimeOpen;
    }

    public void setTempleTimeOpen(String templeTimeOpen) {
        this.templeTimeOpen = templeTimeOpen;
    }

    public String getTempleTimeClose() {
        return templeTimeClose;
    }

    public void setTempleTimeClose(String templeTimeClose) {
        this.templeTimeClose = templeTimeClose;
    }

    public String getTempleAddress() {
        return templeAddress;
    }

    public void setTempleAddress(String templeAddress) {
        this.templeAddress = templeAddress;
    }

    public String getTempleSubDistrictArea() {
        return templeSubDistrictArea;
    }

    public void setTempleSubDistrictArea(String templeSubDistrictArea) {
        this.templeSubDistrictArea = templeSubDistrictArea;
    }

    public String getTempleDistrictArea() {
        return templeDistrictArea;
    }

    public void setTempleDistrictArea(String templeDistrictArea) {
        this.templeDistrictArea = templeDistrictArea;
    }

    public String getTempleProvince() {
        return templeProvince;
    }

    public void setTempleProvince(String templeProvince) {
        this.templeProvince = templeProvince;
    }

    public Integer getTemplePostalCode() {
        return templePostalCode;
    }

    public void setTemplePostalCode(Integer templePostalCode) {
        this.templePostalCode = templePostalCode;
    }

    public String getTempleLatittude() {
        return templeLatittude;
    }

    public void setTempleLatittude(String templeLatittude) {
        this.templeLatittude = templeLatittude;
    }

    public String getTempleLongitude() {
        return templeLongitude;
    }

    public void setTempleLongitude(String templeLongitude) {
        this.templeLongitude = templeLongitude;
    }

    public String getTempleImage() {
        return templeImage;
    }

    public void setTempleImage(String templeImage) {
        this.templeImage = templeImage;
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