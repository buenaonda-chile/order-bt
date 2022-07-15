package com.orderbt.Service;

import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;

import java.util.HashMap;
import java.util.List;

public interface ItemService {
    List<ItemDto> getItem(SearchDto dto);

    List<ItemDto> getItemInfo(Integer itemId);

    HashMap<String, Integer> getItemBoard();

    void saveItemInfo(List<ItemDto> dtos);

    void deleteItemInfo(List<ItemDto> dtos);
}
