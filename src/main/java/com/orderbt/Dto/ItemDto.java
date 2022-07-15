package com.orderbt.Dto;

import lombok.Data;

@Data
public class ItemDto {
    private Integer id;
    private Integer itemId;
    private String category;
    private String name;
    private Integer price;
    private String active;
    private String remark;
    private String type;
    private String detail;
    private String cretDt;
    private String estiCnt;
}
