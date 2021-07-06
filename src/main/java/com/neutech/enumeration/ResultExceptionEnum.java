package com.neutech.enumeration;

public enum ResultExceptionEnum
{
    FORMAT_EXCEPTION(1001,"数据格式异常"),
    DATA_NOT_EXISTS(1002,"数据不存在");

    private Integer code;
    private String message;

    ResultExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
