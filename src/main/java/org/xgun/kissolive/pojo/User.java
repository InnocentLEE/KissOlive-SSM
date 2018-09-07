package org.xgun.kissolive.pojo;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String telphone;

    private String imgUrl;

    private Integer score;

    private Integer role;

    public User(Integer id, String username, String password, String telphone, String imgUrl, Integer score, Integer role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.telphone = telphone;
        this.imgUrl = imgUrl;
        this.score = score;
        this.role = role;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telphone='" + telphone + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", score=" + score +
                ", role=" + role +
                '}';
    }
}