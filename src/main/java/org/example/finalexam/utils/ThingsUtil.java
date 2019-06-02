package org.example.finalexam.utils;

import javax.websocket.Session;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;

/**
 * @author 12589
 */
public class ThingsUtil {

    public static String getTime(){
        long i=System.currentTimeMillis();
        Date date=new Date(i);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM- dd HH:mm:ss");
        return dateFormat.format(date);
    }
    public static int getNumber(){
        Random random=new Random();
        int num=random.nextInt(89999);
        num=num+100000;
        return num;
    }
    public static void broadcast(Set<Session> list, String msg){
        for (Session session : list) {
            try {
                session.getBasicRemote().sendText(ResultUtil.result(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
