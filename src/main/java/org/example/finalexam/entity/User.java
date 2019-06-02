package org.example.finalexam.entity;

import lombok.Data;

/**
 * @author 12589
 */
@Data
public class User {
    private int code;
    private String username;
    private String password;

    public User(int code,String username,String password,int status){
        this.username=username;
        this.password=password;
        this.code=code;
    }
}
