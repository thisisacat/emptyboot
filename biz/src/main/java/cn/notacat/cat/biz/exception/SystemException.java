package cn.notacat.cat.biz.exception;


import cn.notacat.cat.biz.exception.status.EnumStatus;
import cn.notacat.cat.biz.exception.status.StatusCode;

/****************************************
 * @@CREATE : 2018-02-02 上午12:10
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 系统异常类
 * @@VERSION :
 *
 *****************************************/
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private EnumStatus status;
    private String msg;

    public SystemException() {
        this(StatusCode.UNKNOW_ERROR);
    }


    public SystemException(EnumStatus status, String msg) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }

    public SystemException(EnumStatus status) {
        this(status,status.getMsg());
    }

    public SystemException(EnumStatus status,String msg,Throwable cause) {
        super(msg, cause);
        this.status = status;
        this.msg = msg;
    }
    public SystemException(EnumStatus status, Throwable cause) {
        this(status,status.getMsg(),cause);
    }



    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
