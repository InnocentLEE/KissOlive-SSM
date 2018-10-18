package org.xgun.kissolive.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by GvG on 2018/10/13.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * 复写了 registerStompEndpoints() 方法：添加一个服务端点，来接收客户端的连接。将 "/endpointChat" 路径注册为 STOMP 端点。
     * 这个路径与发送和接收消息的目的路径有所不同，这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
     * 即用户发送请求 ：url="/127.0.0.1:8080/endpointChat" 与 STOMP server 进行连接，之后再转发到订阅url；
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加一个/endpointChat端点，客户端就可以通过这个端点来进行连接；withSockJS作用是添加SockJS支持
        registry.addEndpoint("/endpointChat")
                .setAllowedOrigins("*")
                .addInterceptors(new SessionAuthHandshakeInterceptor())
                .withSockJS();
    }

    /**
     * 复写了 configureMessageBroker() 方法：
     * 配置了一个 简单的消息代理，通俗一点讲就是设置消息连接请求的各种规范信息。
     * 发送应用程序的消息将会带有 “/app” 前缀。
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //定义了一个（或多个）客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
        registry.enableSimpleBroker("/queue", "/topic");
        //定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀
        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(createUserInterceptor());
    }

    /**
     *
     * @Title: createUserInterceptor
     * @Description: 将客户端渠道拦截器加入spring ioc容器
     * @return
     */
    @Bean
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }
}
