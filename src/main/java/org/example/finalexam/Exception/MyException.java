package org.example.finalexam.Exception;

import lombok.extern.slf4j.Slf4j;
import org.example.finalexam.entity.enums.ResultEnums;

/**
 * @author 12589
 */
@Slf4j
public class MyException extends Exception {
     private  int code;
     public MyException(ResultEnums resultEnums){
         super(resultEnums.getMsg());
         this.code=resultEnums.getCode();
     }
     public MyException(ResultEnums resultEnums,Class<?>clazz){
         super(resultEnums.getMsg());
         this.code=resultEnums.getCode();
         log.error(resultEnums.getMsg());
     }
     public MyException(ResultEnums resultEnums,String msg){
         super(msg);
         this.code=resultEnums.getCode();
     }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
