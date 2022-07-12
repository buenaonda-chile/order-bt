package com.orderbt.Dto;

import lombok.Data;

@Data
public class MessageDto {
    private String from;
    private String to;
    private String text;
}
