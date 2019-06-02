package org.example.finalexam.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * @author 12589
 */
public class NamesUtils {
    public static final String CONNECT="connect";
    public static final String CHESS="chess";
    public static final int MAXSIZE=15;
    public static final String START="start";
    public static final String BLACK="black";
    public static final String WHITE ="white";
    /**
     * 棋盘大小
     * */
    public static final int [][] CHESS_BOARD=new int[MAXSIZE][MAXSIZE];
    /**
     * 棋盘的格子里的值
     * */

    public static void initialize(){
        for (int i=0;i<MAXSIZE;i++){
            for (int j=0;j<MAXSIZE;j++){
                CHESS_BOARD[i][j]=0;
            }
        }
    }
}
