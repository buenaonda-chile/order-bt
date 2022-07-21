package com.orderbt.Dto;

import lombok.Data;

@Data
public class EstimateDto {
    private Long id;
    private String model;
    private String company;
    private String name;
    private String tel;
    private String email;
    private String itemName;
    private String cretDt;
    private String type;
    private String date;
    private String consultYn;
    private String consultDt;
}
