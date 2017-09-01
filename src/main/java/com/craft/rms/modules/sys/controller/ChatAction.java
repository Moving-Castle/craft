package com.craft.rms.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.craft.rms.modules.sys.model.message.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengpei on 2017/8/25.
 */
@RestController
public class ChatAction {
    /**
     * 表示服务端可以接收客户端通过主题“/app/hello”发送过来的消息，客户端需要在主题"/topic/hello"上监听并接收服务端发回的消息
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ChatMessage broadcast(ChatMessage chatMessage){
        System.out.println("收到的信息：" + JSON.toJSONString(chatMessage));
        return chatMessage;
    }
    /**
     * 这里用的是@SendToUser，这就是发送给单一客户端的标志。本例中，
     * 客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ,这里的用户id可以是一个普通的字符串，只要每个用户端都使用自己的id并且服务端知道每个用户的id就行。
     * @return
     */
    @MessageMapping("/message")
    @SendToUser("/message")
    public ChatMessage handleSubscribe(ChatMessage chatMessage){
        System.out.println("单一客户端：" + JSON.toJSONString(chatMessage));
        return chatMessage;
    }
}
