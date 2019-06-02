package org.example.finalexam.utils;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

import javax.websocket.Session;

/**
 * @author 12589
 */
@Data
public class UserContextUtil {
    private Session session;
    private  int gameStatus;
    public UserContextUtil(Session session){
        this.session=session;
    }
    public interface GameStatus{
        int UN_READY =0;
        int READY =1;
        int IN_GAME =2;
    }
    public boolean isReady(){
        return gameStatus==GameStatus.READY;
    }
}
