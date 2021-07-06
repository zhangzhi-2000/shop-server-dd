package com.neutech.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResultVO {
    private Integer code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    private static final Integer DEFAULT_SUCCESS_CODE=0;
    private static final String DEFAULT_SUCCESS_MSG="成功";

    private static final ResultVO DEFAULT_SUCCESS=new ResultVO(DEFAULT_SUCCESS_CODE,DEFAULT_SUCCESS_MSG);

    public static ResultVO success(){
        return DEFAULT_SUCCESS;
    }

    public ResultVO(){
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static ResultVO success(Object data){
        ResultVO resultVO=new ResultVO();
        resultVO.code=DEFAULT_SUCCESS_CODE;
        resultVO.msg=DEFAULT_SUCCESS_MSG;
        resultVO.data=data;
        return resultVO;
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.code=code;
        resultVO.msg=msg;
        return resultVO;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
