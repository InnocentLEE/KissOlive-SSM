package org.xgun.kissolive.vo;

/**
 * Created by GvG on 2018/10/15.
 */
public class ChatUserList {

    private Integer userId;

    private String name;

    private String imgUrl;

    private Integer num;

    public ChatUserList(Integer userId, String name, String imgUrl, Integer num) {
        this.userId = userId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.num = num;
    }

    public ChatUserList(){
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
