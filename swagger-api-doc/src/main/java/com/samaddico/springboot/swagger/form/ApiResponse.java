package com.samaddico.springboot.swagger.form;

import lombok.Data;

@Data
public class ApiResponse {

    private boolean success;
    private Object data;
    private String message;

}
