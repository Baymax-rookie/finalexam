package org.example.finalexam.controller.websocket;

import org.example.finalexam.entity.resultentity.Message;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

import static org.example.finalexam.utils.ThingsUtil.broadcast;

/**
 * @author 12589
 */
@ServerEndpoint("/websocket/{roomnum}")
public class PublicChatRoom {
    private String username;
    private static  Map<String, Set<Session>> SESSIONS =new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("roomnum")String room, Session session, EndpointConfig config){
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.username= (String) httpSession.getAttribute("username");

        if (SESSIONS.containsKey(room)){
            SESSIONS.get(room).add(session);
        }else {
            Set<Session>sessionSet=new HashSet<>();
            sessionSet.add(session);
            SESSIONS.put(room,sessionSet);
        }
        Message message=new Message();
        message.setWelcome(username);
        broadcast(SESSIONS.get(room),message.toJson());
    }

    @OnClose
    public void onClose(@PathParam("roomnum")String room,Session session) throws IOException {
        SESSIONS.get(room).remove(session);
        Message message =new Message();
        message.setGoodbye(username);
        broadcast(SESSIONS.get(room),message.toJson());
        session.close();
    }
    @OnMessage
    public void msg(@PathParam("roomnum")String room,String msg){
        Message message=new Message();
        message.setContext(this.username,msg);
        broadcast(SESSIONS.get(room),message.toJson());
    }

}
