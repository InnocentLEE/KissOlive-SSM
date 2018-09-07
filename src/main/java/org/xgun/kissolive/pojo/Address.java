package org.xgun.kissolive.pojo;

public class Address {
    private Integer id;

    private Integer userId;

    private String province;

    private String city;

    private String district;

    private String detail;

    private String postcode;

    private String consignee;

    private String telphone;

    public Address(Integer id, Integer userId, String province, String city, String district, String detail, String postcode, String consignee, String telphone) {
        this.id = id;
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.district = district;
        this.detail = detail;
        this.postcode = postcode;
        this.consignee = consignee;
        this.telphone = telphone;
    }

    public Address() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", detail='" + detail + '\'' +
                ", postcode='" + postcode + '\'' +
                ", consignee='" + consignee + '\'' +
                ", telphone='" + telphone + '\'' +
                '}';
    }
}