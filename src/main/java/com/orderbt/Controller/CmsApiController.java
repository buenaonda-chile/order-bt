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

    @PostMapping("/item")
    public Integer saveItem(ItemDto dto){
        return itemService.saveItem(dto);
    }

    @PutMapping("/item")
    public void updateItem(ItemDto dto){
        itemService.updateItem(dto);
    }

    @DeleteMapping("/item")
    public void deleteItem(ItemDto dto){
        itemService.deleteItem(dto);
    }

    @DeleteMapping("/items")
    public void deleteItems(@RequestBody List<ItemDto> dtos){
        itemService.deleteItems(dtos);
    }

    @PutMapping("/items")
    public void updateItems(@RequestBody List<ItemDto> dtos){
        itemService.updateItems(dtos);
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
