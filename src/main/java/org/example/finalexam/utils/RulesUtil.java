package org.example.finalexam.utils;

import org.example.finalexam.entity.playentity.Move;

import static org.example.finalexam.utils.NamesUtils.*;

/**
 * @author 12589
 */
public class RulesUtil {
    private static int left;
    private static int right;
    /**
     * how to do it
     * */
    public boolean isSuccess(){
        return false;
    }

    /**
     * 弟弟行为
     * */
    public static  boolean isRange(int x){
        left=x+1;
        right=abs(15-x);
        return (left + right) == 15;
    }
    private static Integer abs(Integer a){
        return a>0?a:-a;
    }
    public static void turn(Move move){
        if (move.getColor().equals(BLACK)){
            move.setColor(WHITE);
        } else {
            move.setColor(BLACK);
        }
        move.setX(0);
        move.setY(0);
    }

}
