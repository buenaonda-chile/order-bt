package com.orderbt.Dto;

import lombok.Data;

@Data
public class CtnsOrderDto {
    private int id;
    private int estimateId;
    private String item;
    private String spec;
    private String qty;
    private int price;
    private int supplyPrice;
    private int tax;
    private String remark;
}
