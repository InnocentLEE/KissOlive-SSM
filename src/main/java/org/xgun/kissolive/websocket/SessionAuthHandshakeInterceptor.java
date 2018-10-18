package org.xgun.kissolive.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.Random;

/**
 * Created by GvG on 2018/10/13.
 */
public class SessionAuthHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        //获取session里用户信息userId
        //UserDO user = ShiroUtils.getUser();
        //测试用userId 1
        Integer userId =  new Random().nextInt(100)+1;
        if (userId == null) {
            //用户未登录
            return false;
        }
        attributes.put("WEBSOCKET_USERID", userId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
        //握手之后
    }

}