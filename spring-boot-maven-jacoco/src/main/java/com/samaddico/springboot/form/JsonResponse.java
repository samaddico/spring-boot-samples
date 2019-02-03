package com.samaddico.springboot.form;

import lombok.Data;

@Data
public class JsonResponse {

    public boolean success;
    public Object data;
    public String message;

}
