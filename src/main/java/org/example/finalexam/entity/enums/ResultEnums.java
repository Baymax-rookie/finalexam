package org.example.finalexam.entity.enums;

/**
 * @author 12589
 */

public enum ResultEnums {
    /**
     * succeed ,就是各种成功，放一堆吧。。
     * */
    OK(1000,"成功"),
    /**
     * 登录失败
     */
    LOGINERROR(2001,"登陆失败"),
    /**
     * 注册失败
     * */
    SIGNERROR(2002,"注册失败"),
    /**
     * 发送消息失败  这个enum好像没什么用，
     * */
    SENDERROR(2003,"发送消息失败"),
    /**
     * 落子失败    这个好像也是
     * */
    CHESSERROR(2004,"落子失败"),
    /**
     * 选取用户失败
     * */
    GETUSERERROR(2005,"选取用户失败"),
    /**
     * 选取用户失败
     * */
    THREADTESTERROR(2006,"线程测试失败"),
    /**
     * 进入房间失败
     * */
    ENTERERROR(2007,"进房失败"),
    /**
     * 进入房间失败
     * */
    STOREERROR(2008,"存储失败"),
    /**
     * 进入房间失败
     * */
    NOTREADY(2009,"没有准备好");

    private int code;
    private String msg;
    ResultEnums(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
