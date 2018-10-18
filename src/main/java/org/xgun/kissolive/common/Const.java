package org.xgun.kissolive.common;

/**
 * Created by Lee on 2018/5/7.
 */
public class Const {
    public static final String VERIFY_CODE = "verifyCode";//验证码
    public static final String PHONE_NUMBER = "phoneNumber";//手机
    public static final String CURRENT_USER = "current_user";
    public static final String USER_IMG_URL_DEFAULT = "http://119.23.253.135:9090/kissolive/default.png";

    public static final String FTP_SERVER_IP = "119.23.253.135";//ftp服务器ip地址
    public static final String FTP_USERNAME = "innocent";//ftp服务器用户名
    public static final String FTP_PASSWORD = "lyp82nlf";//ftp服务器密码
    public static final String FTP_PREFIX = "ftp://119.23.253.135";//ftp服务器资源访问前缀
    public static final String HTTP_PREFIX = "http://119.23.253.135:9090";//ftp服务器资源访问前缀

    public static final String ALIPAY_CALLBACK = "http://olive.s1.natapp.cc/alipay_callback.do";

    public static final Integer BRAND_PUT_ON_STATUS = 1;//品牌上架状态值
    public static final Integer GOODS_PUT_ON_STATUS = 1;//商品上架状态值
    public static final Integer ID_INIT = 0;//id默认值为0

    public static final String FILE_SAVE_PATH = "/kissolive/";//文件上传目录路径
    public interface Role{
        int ROLE_CUSTOMER = 0; //会员
        int ROLE_ADMIN = 1;//店员
        int RELE_SUPER = 2;//店长
    }
    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum OrderStatusEnum{
        CANCELED(-1,"已取消"),
        NO_PAY(0,"提交了未付款"),
        PAID(1,"付款了未发货"),
        SHIPPED(2,"发货了未收货"),
        HAND(3,"收货了未评价"),
        ORDER_SUCCESS(4,"订单完成");


        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }
}
