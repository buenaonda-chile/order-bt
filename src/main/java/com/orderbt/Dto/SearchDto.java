package com.orderbt.Dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchDto {
    private String category;
    private String keyword;
    private List<String> keywordList;
}
