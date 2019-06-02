package org.example.finalexam.entity.playentity;

import lombok.Data;
import org.example.finalexam.Exception.MyException;
import org.example.finalexam.entity.enums.ResultEnums;
import org.example.finalexam.utils.UserContextUtil;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 12589
 */
@Data
public abstract class AbstractRoom {
    protected String roomId;
    protected int totalNum;
    protected int currentNum;
    protected String createdTime;
    protected static Map<Session, UserContextUtil>sessions=new HashMap<>();

    public boolean enterRoom(Session session){
        if (currentNum<totalNum){
            sessions.put(session,new UserContextUtil(session));
            currentNum++;
            return true;
        }
        return false;
    }

    public void event(){}

    public void gameOver(Session session){
        for (Map.Entry<Session,UserContextUtil>userSessions:sessions.entrySet()){
            userSessions.getValue().setGameStatus(UserContextUtil.GameStatus.UN_READY);
        }
    }

    public void leaveRoom(Session session){
        sessions.remove(session);
        currentNum--;
    }

    /**
    * 本来ThingsUtil有个，但是这里还是写个广播
    */
    public void broadcast(String msg) throws MyException {
        for (Session session:sessions.keySet()){
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                throw new MyException(ResultEnums.SENDERROR);
            }
        }
    }

    public static void startGame(){
        for (Map.Entry<Session, UserContextUtil> each : sessions.entrySet()){
            each.getValue().setGameStatus(UserContextUtil.GameStatus.IN_GAME);
        }
    }

    public void doReady(Session s){
        UserContextUtil userContext = sessions.get(s);
        userContext.setGameStatus(UserContextUtil.GameStatus.READY);
        startGame();
    }



}
