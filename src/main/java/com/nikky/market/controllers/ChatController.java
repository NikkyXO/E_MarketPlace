package com.nikky.market.controllers;

import com.nikky.market.entities.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatmsg,
                                SimpMessageHeaderAccessor headerAccessor) {

        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatmsg.getSender());
        return chatmsg;
    }


    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatmsg) {

        return chatmsg;
    }
}
