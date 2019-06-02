package org.example.finalexam.service;

import org.example.finalexam.entity.Moves;
import org.example.finalexam.entity.playentity.AbstractRoom;
import org.example.finalexam.entity.playentity.Move;
import org.example.finalexam.entity.playentity.PlayRoom;
import org.example.finalexam.mapper.MoveMapper;
import org.example.finalexam.utils.NamesUtils;
import org.example.finalexam.utils.RulesUtil;
import org.example.finalexam.utils.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

import static org.example.finalexam.utils.NamesUtils.CHESS_BOARD;
import static org.example.finalexam.utils.NamesUtils.MAXSIZE;

/**
 * @author 12589
 */
@Service
public class PlayerService {
    @Autowired
    private MoveMapper moveMapper;
    private Map<Session, UserContextUtil> sessions=new HashMap<>();
    public boolean makeMove(Move move) {
        int x = move.getX();
        int y = move.getY();
        if (RulesUtil.isRange(x) && RulesUtil.isRange(y)) {
            if (CHESS_BOARD[x][y] == 0) {
                CHESS_BOARD[x][y] = 1;
                return true;
            }
        }
        return false;
    }
    public boolean store(Moves moves){
       if( moveMapper.insertMoves(moves)){
           return true;
       }else {
           return false;
       }
    }
}
