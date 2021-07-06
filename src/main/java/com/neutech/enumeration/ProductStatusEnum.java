package com.neutech.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProductStatusEnum {
    ON_SALES(1,"在售"),
    OFF_THE_SHELL(2,"下架");
    private Integer statusCode;
    private String statusMsg;

    ProductStatusEnum(Integer statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String sstatusMsg) {
        this.statusMsg = sstatusMsg;
    }

    public static ProductStatusEnum getProductStatusEnum(Integer code){
        for(ProductStatusEnum value:values()){
            if(value.getStatusCode().equals(code))
                return value;
        }
        return null;
    }
}
