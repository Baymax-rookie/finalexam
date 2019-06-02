package org.example.finalexam.utils.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.finalexam.Exception.MyException;
import org.example.finalexam.entity.enums.ResultEnums;
import org.example.finalexam.utils.ResultUtil;

/**
 * @author 12589
 */
@Slf4j
@Data
public class ThreadUtil implements Runnable{
    /**
     *  wdnmd,连程序都跑不起来，还多线程，弃了
     * */
    private RunContextUtil runContextUtil;
    public ThreadUtil(RunContextUtil runContextUtil){
        this.runContextUtil=runContextUtil;
    }
    @Override
    public void run() {
        while(true){
            log.info("目前房间数"+runContextUtil.getRooms().size());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                try {
                    throw new MyException(ResultEnums.THREADTESTERROR);
                } catch (MyException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
