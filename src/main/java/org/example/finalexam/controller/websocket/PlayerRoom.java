package org.example.finalexam.controller.websocket;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.example.finalexam.Exception.MyException;
import org.example.finalexam.entity.Moves;
import org.example.finalexam.entity.enums.ResultEnums;
import org.example.finalexam.entity.playentity.Move;
import org.example.finalexam.entity.playentity.AbstractRoom;
import org.example.finalexam.entity.playentity.PlayRoom;
import org.example.finalexam.entity.resultentity.ResponseBeen;
import org.example.finalexam.service.PlayerService;
import org.example.finalexam.utils.ResultUtil;
import org.example.finalexam.utils.RulesUtil;
import org.example.finalexam.utils.ThingsUtil;
import org.example.finalexam.utils.test.RunContextUtil;
import org.example.finalexam.utils.test.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static org.example.finalexam.utils.NamesUtils.CHESS;
import static org.example.finalexam.utils.NamesUtils.CONNECT;
/**
 * @author 12589
 */
@Slf4j
@ServerEndpoint("/websocket/chess/{chessnum}")  
public class PlayerRoom {
    private Move move;
    private Moves moves;
    public static ConcurrentHashMap<String,AbstractRoom>rooms=new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,AbstractRoom>playRooms=new ConcurrentHashMap<>();
    private Gson gson;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    static {
        RunContextUtil context = new RunContextUtil(rooms);
        ThreadUtil thread = new ThreadUtil(context);
        Thread dThread = new Thread(thread);
        log.info(" Thread Created!");
        dThread.start();
    }

    /**
     * message 这里限制一下格式，
     * 如果字符串是以"connect"开始则进入房间，
     * 如果是以"chess"开头，该message的"chess"之后是String类型的Move对象转成Json的形式,例子在这里{"color":"黑","x":5,"y":5}，需要转化成Move再传回去
     * 否则是确定为在room内该Session进行准备工作
     * */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("roomNum")String roomNum) throws MyException, IOException {
        String roomId=getRoomNum(session,roomNum);
        if (message.startsWith(CONNECT)){
            doConnect(session,roomNum);
        }else if(message.startsWith(CHESS)){
            String content=message.substring(5);
            AbstractRoom room=playRooms.get(roomId);
            move=gson.fromJson(content,Move.class);
            boolean is=playerService.makeMove(move);
            ResponseBeen responseBeen;
            if (is){
                this.moves.setX(move.getX());
                this.moves.setY(move.getY());
                is=playerService.store(moves);
                if (is){
                    responseBeen=new ResponseBeen(ResultEnums.OK.getMsg(),move,is);
                    RulesUtil.turn(move);
                }else {
                    responseBeen=new ResponseBeen(ResultEnums.CHESSERROR.getMsg(),null,is);
                }
            }else {
                responseBeen=new ResponseBeen(ResultEnums.CHESSERROR.getMsg(),null,is);
            }
            room.broadcast(ResultUtil.result(responseBeen));
        }else {
        getReady(session, message,roomNum);
    }
    }
    private String getRoomNum(Session session,@PathParam("chessnum") String roomNum){
        List<String>list=session.getRequestParameterMap().get(roomNum);
        return list.get(0);
    }

    private void getReady(Session session,String message,@PathParam("chessnum") String roomNum) throws MyException {
        AbstractRoom room=getRoom(session,roomNum);
        room.doReady(session);
        room.broadcast(message);
    }

    private AbstractRoom getRoom(Session session,@PathParam("chessnum") String roomNum){
        return rooms.get(getRoomNum(session,roomNum));
    }

    private void doConnect(Session session,@PathParam("chessnum") String roomNum) throws IOException {
        String roomId=getRoomNum(session,roomNum);
        if (rooms.containsKey(roomId)){
            AbstractRoom room=rooms.get(roomId);
            if (room.enterRoom(session)){
                session.getUserProperties().put("roomId",roomId);
            }else {
                ResponseBeen responseBeen =new ResponseBeen(ResultEnums.ENTERERROR,null);
                session.getBasicRemote().sendText(ResultUtil.result(responseBeen));
            }
        }else{
            AbstractRoom room=new PlayRoom(roomId,2);
            if(room.enterRoom(session)){
                playRooms.put(roomNum,room);
                session.getUserProperties().put("roomId", roomId);
            }
        }
    }
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig,@PathParam("chessnum") String roomNum){
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        String username=redisTemplate.opsForValue().get("username");
        username = (String) httpSession.getAttribute("username");
        AbstractRoom room=getRoom(session,roomNum);
        rooms.put(username,room);
        log.info("Connected");
    }

    @OnClose
    public void onClose (Session session) {
        String roomId = (String)session.getUserProperties().get("roomId");
        if (roomId != null){
            AbstractRoom room = rooms.get(roomId);
            if (room != null){
                room.leaveRoom(session);
                if (room.getCurrentNum() <= 0){
                    rooms.remove(roomId);
                }
            }
        }
        System.out.println("Connection closed");
    }
}
