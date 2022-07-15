package com.orderbt.Controller;

import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;
import com.orderbt.Service.Impl.ItemServiceImpl;
import com.orderbt.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms/api")
@RequiredArgsConstructor
public class CmsApiController {

    private final ItemService itemService;

    @GetMapping("/item")
    public List<ItemDto> getItem(SearchDto dto){
        return itemService.getItem(dto);
    }

    @GetMapping("/itemInfo")
    public List<ItemDto> getItemInfo(@RequestParam Integer itemId){
        return itemService.getItemInfo(itemId);
    }

    @PutMapping("/itemInfo")
    @ResponseBody
    public List<ItemDto> saveItemInfo(@RequestBody List<ItemDto> dtos){
        itemService.saveItemInfo(dtos);
        return itemService.getItemInfo(dtos.get(0).getItemId());
    }

    @DeleteMapping("/itemInfo")
    @ResponseBody
    public List<ItemDto> deleteItemInfo(@RequestBody List<ItemDto> dtos){
        itemService.deleteItemInfo(dtos);
        return itemService.getItemInfo(dtos.get(0).getItemId());
    }
}
