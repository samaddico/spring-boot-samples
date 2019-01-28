package com.samaddico.springboot.swagger.form;


import lombok.Data;

@Data
public class ApiRequest {

    private String search;
    private Integer offset;
    private Integer limit;
    private String  order;

}
