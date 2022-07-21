package com.orderbt.Dto;

import lombok.Data;

@Data
public class CtnsEstimateDto {
    private int estimateId;
    private String deliveryDt;
    private String effectiveDt;
    private String address;
    private String remark;
}
