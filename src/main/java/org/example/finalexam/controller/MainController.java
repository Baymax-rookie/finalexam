package org.example.finalexam.controller;

import org.example.finalexam.Exception.MyException;
import org.example.finalexam.entity.User;
import org.example.finalexam.entity.enums.ResultEnums;
import org.example.finalexam.entity.playentity.AbstractRoom;
import org.example.finalexam.entity.playentity.PlayRoom;
import org.example.finalexam.entity.resultentity.ResponseBeen;
import org.example.finalexam.service.UserService;
import org.example.finalexam.utils.NamesUtils;
import org.example.finalexam.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.concurrent.TimeUnit;

import static org.example.finalexam.controller.websocket.PlayerRoom.playRooms;
import static org.example.finalexam.controller.websocket.PlayerRoom.rooms;
import static org.example.finalexam.entity.playentity.AbstractRoom.startGame;

/**
 * @author 12589
 */
@RestController("/user")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @RequestMapping("/sign")
    public String signIn(HttpServletRequest request){

        ResponseBeen responseBeen=userService.sign(request);
        User user= (User) responseBeen.getObject();
        redisTemplate.opsForValue().set("username",user.getUsername(),60*10,TimeUnit.MINUTES);
        return ResultUtil.result(responseBeen);
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws MyException {
        ResponseBeen responseBeen=userService.logUser(request);
        User user= (User) responseBeen.getObject();
        redisTemplate.opsForValue().set("username",user.getUsername(),60*10,TimeUnit.MINUTES);
        return ResultUtil.result(responseBeen);
    }
    @RequestMapping("/getuser")
    public String getUser(String username){
        Object obj=userService.getUser(username);
        return ResultUtil.result(obj);
    }
    @RequestMapping("/initialize")
    public void init(){
        NamesUtils.initialize();
    }
    @RequestMapping("/getplayer")
    public Object getplayer(){
        if (playRooms.keySet().size()==2){
            startGame();
        }
        return ResultUtil.result(ResultEnums.NOTREADY);
    }
    @RequestMapping("/createroom")
    public void create(Session session,HttpServletRequest request){
        HttpSession httpSession=request.getSession();
        String roomId=request.getParameter("roomId");
        String username= (String) httpSession.getAttribute("username");
        AbstractRoom room=new PlayRoom(roomId,10);
        rooms.put(username,room);
    }

}
