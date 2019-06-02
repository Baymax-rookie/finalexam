package org.example.finalexam.entity.playentity;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.example.finalexam.entity.enums.ResultEnums;
import org.example.finalexam.entity.resultentity.ResponseBeen;
import org.example.finalexam.utils.ThingsUtil;

import javax.websocket.Session;

import java.io.IOException;

import static org.example.finalexam.utils.NamesUtils.*;

/**
 * @author 12589
 */
@Slf4j
public class PlayRoom extends AbstractRoom {
    private Move move;

    public PlayRoom(String roomId,int totalNum){
        this.roomId=roomId;
        this.totalNum=totalNum;
        this.createdTime= ThingsUtil.getTime();
    }
    @Override
    public void event(){
        int temp=0;
        for (Session session:sessions.keySet()){
            move=new Move();
            if (temp%2==0){
                move.setColor(BLACK);
            }else {
                move.setColor(WHITE);
            }
            ResponseBeen responseBeen=new ResponseBeen(ResultEnums.OK.getMsg(),move,true);
            try {
                session.getBasicRemote().sendText(new Gson().toJson(responseBeen));
            } catch (IOException e) {
               log.error(ResultEnums.CHESSERROR.getMsg());
            }
            temp++;
        }
        startGame();
    }


    public static void main(String[] args) {
        Move move=new Move();
        System.out.println(move.getQueue());

    }
}
