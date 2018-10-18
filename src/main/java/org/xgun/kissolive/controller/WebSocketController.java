package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.xgun.kissolive.pojo.ChatMessage;
import org.xgun.kissolive.service.ISilentService;
import org.xgun.kissolive.utils.DateTimeUtil;
import org.xgun.kissolive.vo.Message;

import java.security.Principal;

/**
 * Created by GvG on 2018/10/13.
 */
@Controller
public class WebSocketController<iSilentService> {

    @Autowired
    public SimpMessagingTemplate template;

    @Autowired
    private ISilentService iSilentService;

    /**
     *
     * @param adminMessage
     * @param principal
     * @throws Exception
     */
    @MessageMapping("/message")
/*    @SendToUser("/topic/message")*/
    public void userMessage(Message adminMessage, Principal principal) throws Exception {
        System.out.println("管理员ID:"+ principal.getName()+" 发送了一条信息给用户ID:"+adminMessage.getTo());
        Integer userId = Integer.parseInt(adminMessage.getTo());

        //存储进数据库未读状态
        ChatMessage chatMessage = new ChatMessage(null,adminMessage.getMessage(),0,userId,
                2, DateTimeUtil.strToDate(adminMessage.getDatetime()));
        iSilentService.sendingNewMessage(chatMessage);

        template.convertAndSendToUser(adminMessage.getTo(), "/topic/message", new Message(adminMessage.getMessage(),adminMessage.getDatetime(), 2,
                adminMessage.getTo(),adminMessage.getFrom()));
    }

    @MessageMapping("/toAdmin")
/*    @SendTo("/topic/toAdmin")*/
    public void getUserMessage(Message userMessage, Principal principal) throws Exception {
        System.out.println("用户ID:"+ principal.getName()+" 发送了一条信息");
        Integer userId = Integer.parseInt(userMessage.getFrom());

        //存储进数据库未读状态
        //status=0表示未读，source=1表示类型为用户信息
        ChatMessage chatMessage = new ChatMessage(null,userMessage.getMessage(),0,
                userId,1, DateTimeUtil.strToDate(userMessage.getDatetime()));
        iSilentService.sendingNewMessage(chatMessage);

        template.convertAndSend("/topic/toAdmin",new Message(userMessage.getMessage(),userMessage.getDatetime(), 1,
                userMessage.getTo(),userMessage.getFrom()));
    }
}
