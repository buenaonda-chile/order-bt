package com.orderbt.Mapper;

import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemDto> getItem(SearchDto dto);

    List<ItemDto> getItemInfo(Integer itemId);
    HashMap<String, Integer> getItemBoard();

    void saveItemInfo(ItemDto dto);

    void deleteItemInfo(ItemDto dto);
}
