package org.xgun.kissolive.common;

/**
 * Created by Lee on 2018/5/7.
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String VERIFY_CODE = "verifyCode";
    public interface Role{
        int ROLE_CUSTOMER = 0; //会员
        int ROLE_ADMIN = 1;//店员
        int RELE_SUPER = 2;//店长
    }
}
