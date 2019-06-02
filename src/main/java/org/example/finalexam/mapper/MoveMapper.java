package org.example.finalexam.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.finalexam.entity.Moves;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 12589
 */
@Mapper
@Repository
public interface MoveMapper {
    /**
     * 将五子棋的落子数据存入数据库，虽然没有做出来，但是起码有有个mapper自我安慰一下 XD
     * @param moves Moves 棋子落子的数据类
     * @return boolean is
     * */
    @Insert("Insert into moves queue=#{queue},x=#{x},y=#{y}")
    boolean insertMoves(Moves moves);
    /**
     * 将五子棋的落子数据存入数据库，虽然没有做出来，但是起码有有个mapper自我安慰一下 XD
     * @return boolean is
     * */
    @Select("Select * from moves")
    List<Moves> getMoves();
}
