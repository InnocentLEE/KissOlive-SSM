package org.xgun.kissolive.common;

/**
 * Created by Lee on 2018/5/7.
 */
public class Const {
    public static final String VERIFY_CODE = "verifyCode";//验证码
    public static final String PHONE_NUMBER = "phoneNumber";//手机
    public static final String FTP_SERVER_IP = "119.23.253.135";//ftp服务器ip地址
    public static final String FTP_USERNAME = "";//ftp服务器用户名
    public static final String FTP_PASSWORD = "";//ftp服务器密码
    public static final String FTP_PREFIX = "ftp://119.23.253.135";//ftp服务器资源访问前缀
    public static final String HTTP_PREFIX = "http://119.23.253.135:9090";//ftp服务器资源访问前缀
    public interface Role{
        int ROLE_CUSTOMER = 0; //会员
        int ROLE_ADMIN = 1;//店员
        int RELE_SUPER = 2;//店长
    }
}
