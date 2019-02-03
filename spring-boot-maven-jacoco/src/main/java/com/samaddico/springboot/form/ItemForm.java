package com.samaddico.springboot.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemForm {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String category;

}
