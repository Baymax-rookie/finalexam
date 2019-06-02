package org.example.finalexam.controller.websocket;

import org.example.finalexam.entity.resultentity.Message;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.example.finalexam.utils.ThingsUtil.broadcast;

/**
 * @author 12589
 */
@ServerEndpoint("/websocket/personal/{hostnum}")
public class PrivateChatRoom {
    private static  Set<PrivateChatRoom> ROOM_SET =new CopyOnWriteArraySet<>();
    private Set<Session>sessions=new HashSet<>();
    private String name;
    private static final int CAPACITY =2;
    @OnOpen
    public void onOpen(@PathParam("hostnum") String hostname, Session session, EndpointConfig config) throws IOException {
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        name= (String) httpSession.getAttribute("username");
        sessions.add(session);
        sessions.add(session);
        if (ROOM_SET.size() > CAPACITY) {
            System.out.println("房间人满");
            session.getBasicRemote().sendText("房间人已满!");
        } else {
            Message message=new Message();
            message.setWelcome(name);
            broadcast(sessions,message.toJson());
        }
    }
    @OnClose
    public void onClose(Session session){
        ROOM_SET.remove(this);
        sessions.remove(session);
        Message message=new Message();
        message.setGoodbye(name);
        broadcast(sessions,message.toJson());
    }
    @OnMessage
    public void onMessage(@PathParam("roomnum")String room,String msg){
        Message message=new Message();
        message.setContext(name,msg);
        broadcast(sessions,message.toJson());

    }
}
