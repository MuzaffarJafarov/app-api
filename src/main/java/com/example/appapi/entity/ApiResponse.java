package com.example.appapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String msg;
    private Boolean isSuccess = false;
    private Object object;

    public ApiResponse(String msg, Boolean isSuccess) {
        this.msg = msg;
        this.isSuccess = isSuccess;
    }

    public ApiResponse(Object object) {
        this.object = object;
    }
}
