package com.orderbt.Service;

import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;

import java.util.HashMap;
import java.util.List;

public interface ItemService {
    List<ItemDto> getItem(SearchDto dto);

    Integer saveItem(ItemDto dto);

    void deleteItem(ItemDto dto);

    void deleteItems(List<ItemDto> dtos);

    void updateItem(ItemDto dto);

    void updateItems(List<ItemDto> dtos);

    List<ItemDto> getItemInfo(Integer itemId);

    HashMap<String, Integer> getItemBoard();

    void saveItemInfo(List<ItemDto> dtos);

    void deleteItemInfo(List<ItemDto> dtos);
}
