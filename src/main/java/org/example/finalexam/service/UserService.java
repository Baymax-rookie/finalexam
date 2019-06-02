package org.example.finalexam.service;

import org.example.finalexam.Exception.MyException;
import org.example.finalexam.entity.resultentity.ResponseBeen;
import org.example.finalexam.entity.enums.ResultEnums;
import org.example.finalexam.entity.User;
import org.example.finalexam.mapper.UserMapper;
import org.example.finalexam.utils.ThingsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 12589
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public ResponseBeen sign(HttpServletRequest request){
        ResponseBeen responseBeen;
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        boolean is=userMapper.insertUser(ThingsUtil.getNumber(),username,password);
        if (is){
            responseBeen= new ResponseBeen(ResultEnums.OK,is);
        }else {
            responseBeen=new ResponseBeen(ResultEnums.SIGNERROR,is);
        }
        return responseBeen;
    }
    public ResponseBeen logUser(HttpServletRequest request) throws MyException {
        ResponseBeen responseBeen;
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=userMapper.selectUser(username,password);
        if (user!=null){
            responseBeen=new ResponseBeen(ResultEnums.OK,user);
            return responseBeen;
        }else {
            throw new MyException(ResultEnums.LOGINERROR,UserService.class);
        }
    }
    public User getUser(String username){
        User user=userMapper.selectUser(username);
        if (user!=null){
            return user;
        }else {
            return null;
        }
    }

}
