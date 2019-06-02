package org.example.finalexam.entity.resultentity;

import com.google.gson.Gson;

import lombok.Data;
import org.example.finalexam.utils.ThingsUtil;
import java.util.List;

/**
 * @author 12589
 */
@Data
public class Message {

    private String welcome;
    private String goodbye;
    private static Gson gson;
    private List<String>list;
    private String context;
    public Message(){}
    public void setWelcome(String username) {
        this.welcome = "欢迎"+username+"来到聊天室";
    }
    public void setGoodbye(String username){
        this.goodbye=username+"离开了聊天室";
    }
    public void setContext(String username,String msg){
        this.context=username+ ThingsUtil.getTime() + ":\\n"+
                "  "+msg;
    }
    public Message(String context,List<String>list){
        this.context=context;
        this.list=list;
    }
    public Message (String welcome,String goodbye){
        this.welcome=welcome;
        this.goodbye=goodbye;
    }

    public String toJson(){
        return gson.toJson(this);
    }
}
