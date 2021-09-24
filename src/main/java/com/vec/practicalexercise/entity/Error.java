package com.vec.practicalexercise.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "An error.", type = "object", requiredProperties = {"code", "message"})
public class Error {

    private String code;
    private String message;

    public Error(String code, String message){
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
