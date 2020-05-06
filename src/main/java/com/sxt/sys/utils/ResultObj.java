package com.sxt.sys.utils;

import com.sxt.sys.constast.SysConstast;

public class ResultObj {
    private Integer code = 0;
    private String msg;

    public static final ResultObj ADD_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.ADD_SUCCESS);
    public static final ResultObj ADD_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.ADD_ERROR);
    public static final ResultObj UPDATE_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.UPDATE_SUCCESS);
    public static final ResultObj UPDATE_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.UPDATE_ERROR);
    public static final ResultObj DELETE_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.DELETE_SUCCESS);
    public static final ResultObj DELETE_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.DELETE_ERROR);
    public static final ResultObj RESET_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.RESET_SUCCESS);
    public static final ResultObj RESET_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.RESET_ERROR);
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.DISPATCH_SUCCESS);
    public static final ResultObj DISPATCH_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.DISPATCH_ERROR);

    /**
     * 状态码0
     */
    public static final ResultObj STATUS_TRUE = new ResultObj(SysConstast.CODE_SUCCESS);

    /**
     * 状态码-1
     */
    public static final ResultObj STATUS_ERROR = new ResultObj(SysConstast.CODE_ERROR);


    private ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultObj(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
