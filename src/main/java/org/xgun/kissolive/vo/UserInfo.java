package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.Address;
import org.xgun.kissolive.pojo.User;

import java.util.List;

public class UserInfo {
    private User user;
    private List<Address> addressList;

    public UserInfo() {
    }

    public UserInfo(User user, List<Address> addressList) {
        this.user = user;
        this.addressList = addressList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user=" + user +
                ", addressList=" + addressList +
                '}';
    }
}
