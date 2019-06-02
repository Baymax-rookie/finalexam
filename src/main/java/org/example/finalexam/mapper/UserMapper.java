package org.example.finalexam.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.finalexam.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author 12589
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 插入用户
     * @param code int code
     * @param username String name
     * @param password String password
     * @return boolean
     * */
    @Insert("Insert into user SET code=#{code},username=#{username},password=#{password}")
    public boolean insertUser(int code,String username,String password);

    /**
     * 从数据库中得到用户信息
     * @param username String username
     * @param password String password
     * @return User
     * */
    @Select("SELECT * FROM user where username=#{username},password=#{password}")
    User selectUser(String username,String password);
    /**
     * 从数据库中得到用户信息
     * @param username String username
     * @return User
     * */
    @Select("SELECT * FROM user where username=#{username}")
    User selectUser(String username);
    /**
     * 返回list集合
     * @return ArrayList
     * */
    @Select("Select * From user")
    ArrayList<User> listSelect();

}
