package org.example.finalexam.entity.playentity;

import lombok.Data;

import static org.example.finalexam.utils.NamesUtils.START;

/**
 * @author 12589
 */
@Data
public class Move {
    private String color;
    private int queue=0;
    private int x;
    private int y;
    public Move(){
        this.queue++;
        this.color=START;
    }


}
