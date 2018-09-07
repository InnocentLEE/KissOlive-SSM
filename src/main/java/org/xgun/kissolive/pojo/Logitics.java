package org.xgun.kissolive.pojo;

public class Logitics {
    private Integer orderId;

    private String goodsDetails;

    private Integer weight;

    private String sentName;

    private String sentTel;

    private String sentProvince;

    private String sentCity;

    private String sentDistrict;

    private String sentDetail;

    private String receiveName;

    private String receiveTel;

    private String receiveProvince;

    private String receiveCity;

    private String receiveDistrict;

    private String receiveDetail;

    private Integer status;

    public Logitics(Integer orderId, String goodsDetails, Integer weight, String sentName, String sentTel, String sentProvince, String sentCity, String sentDistrict, String sentDetail, String receiveName, String receiveTel, String receiveProvince, String receiveCity, String receiveDistrict, String receiveDetail, Integer status) {
        this.orderId = orderId;
        this.goodsDetails = goodsDetails;
        this.weight = weight;
        this.sentName = sentName;
        this.sentTel = sentTel;
        this.sentProvince = sentProvince;
        this.sentCity = sentCity;
        this.sentDistrict = sentDistrict;
        this.sentDetail = sentDetail;
        this.receiveName = receiveName;
        this.receiveTel = receiveTel;
        this.receiveProvince = receiveProvince;
        this.receiveCity = receiveCity;
        this.receiveDistrict = receiveDistrict;
        this.receiveDetail = receiveDetail;
        this.status = status;
    }

    public Logitics() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails == null ? null : goodsDetails.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSentName() {
        return sentName;
    }

    public void setSentName(String sentName) {
        this.sentName = sentName == null ? null : sentName.trim();
    }

    public String getSentTel() {
        return sentTel;
    }

    public void setSentTel(String sentTel) {
        this.sentTel = sentTel == null ? null : sentTel.trim();
    }

    public String getSentProvince() {
        return sentProvince;
    }

    public void setSentProvince(String sentProvince) {
        this.sentProvince = sentProvince == null ? null : sentProvince.trim();
    }

    public String getSentCity() {
        return sentCity;
    }

    public void setSentCity(String sentCity) {
        this.sentCity = sentCity == null ? null : sentCity.trim();
    }

    public String getSentDistrict() {
        return sentDistrict;
    }

    public void setSentDistrict(String sentDistrict) {
        this.sentDistrict = sentDistrict == null ? null : sentDistrict.trim();
    }

    public String getSentDetail() {
        return sentDetail;
    }

    public void setSentDetail(String sentDetail) {
        this.sentDetail = sentDetail == null ? null : sentDetail.trim();
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel == null ? null : receiveTel.trim();
    }

    public String getReceiveProvince() {
        return receiveProvince;
    }

    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince == null ? null : receiveProvince.trim();
    }

    public String getReceiveCity() {
        return receiveCity;
    }

    public void setReceiveCity(String receiveCity) {
        this.receiveCity = receiveCity == null ? null : receiveCity.trim();
    }

    public String getReceiveDistrict() {
        return receiveDistrict;
    }

    public void setReceiveDistrict(String receiveDistrict) {
        this.receiveDistrict = receiveDistrict == null ? null : receiveDistrict.trim();
    }

    public String getReceiveDetail() {
        return receiveDetail;
    }

    public void setReceiveDetail(String receiveDetail) {
        this.receiveDetail = receiveDetail == null ? null : receiveDetail.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}