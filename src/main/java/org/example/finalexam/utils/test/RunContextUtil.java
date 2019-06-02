package org.example.finalexam.utils.test;

import lombok.Data;
import org.example.finalexam.entity.playentity.AbstractRoom;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 12589
 */
@Data
public class RunContextUtil {
    private ConcurrentHashMap<String, AbstractRoom>rooms;
    public RunContextUtil(ConcurrentHashMap<String, AbstractRoom>rooms){
        this.rooms=rooms;
    }
}
