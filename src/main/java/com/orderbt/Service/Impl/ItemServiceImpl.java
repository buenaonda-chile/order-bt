package com.orderbt.Service.Impl;


import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;
import com.orderbt.Mapper.ItemMapper;
import com.orderbt.Service.ItemService;
import com.orderbt.Util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getItem(SearchDto dto) {
        if(dto.getKeyword() != null)
            dto.setKeywordList(Util.makeForeach(dto.getKeyword(), ","));

        return itemMapper.getItem(dto);
    }

    @Override
    public List<ItemDto> getItemInfo(Integer itemId) {
        return itemMapper.getItemInfo(itemId);
    }

    @Override
    public HashMap<String, Integer> getItemBoard() {
        return itemMapper.getItemBoard();
    }

    @Override
    public void saveItemInfo(List<ItemDto> dtos) {
        for(ItemDto dto : dtos){
            itemMapper.saveItemInfo(dto);
        }
    }

    @Override
    public void deleteItemInfo(List<ItemDto> dtos) {
        for(ItemDto dto : dtos){
            itemMapper.deleteItemInfo(dto);
        }
    }
}
