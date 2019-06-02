package org.example.finalexam.entity;

import lombok.Data;

/**
 * @author 12589
 */
@Data
public class Moves {
    private int queue=0;
    private int x=-1;
    private int y=-1;
    public Moves(int x,int y){
        this.queue++;
        this.x=x;
        this.y=y;
    }

    public void setX(int x) {
        this.x = x;
        this.queue++;
    }
}
