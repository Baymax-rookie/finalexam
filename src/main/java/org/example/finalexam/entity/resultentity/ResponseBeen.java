package org.example.finalexam.entity.resultentity;

import lombok.Data;
import org.example.finalexam.entity.enums.ResultEnums;

/**
 * @author 12589
 */
@Data
public class ResponseBeen {
    private String msg;
    private Object object;
    private boolean isSuccess;
    public ResponseBeen(ResultEnums resultEnums, Object obj){
        this.msg=resultEnums.getMsg();
        this.object=obj;
    }
    public ResponseBeen(String msg,Object object,boolean isSuccess){
        this.msg=msg;
        this.object=object;
        this.isSuccess=isSuccess;
    }
}
