package com.orderbt.Controller;

import com.orderbt.Domain.Estimate;
import com.orderbt.Dto.EstimateDto;
import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;
import com.orderbt.Dto.StaffDto;
import com.orderbt.Service.EstimateService;
import com.orderbt.Service.Impl.ItemServiceImpl;
import com.orderbt.Service.ItemService;
import com.orderbt.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms/api")
@RequiredArgsConstructor
public class CmsApiController {

    private final ItemService itemService;
    private final EstimateService estimateService;

    private final StaffService staffService;

    @GetMapping("/staff")
    public List<StaffDto> getStaffGrid(SearchDto dto) { return staffService.getStaffGrid(dto); }

    @PostMapping("/staff")
    public void joinStaff(StaffDto dto){ staffService.joinStaff(dto);}

    @PutMapping("/staff")
    public void updateStaff(StaffDto dto){staffService.updateStaff(dto);}

    @DeleteMapping("/staff")
    public void deleteStaff(@RequestParam String id) {staffService.deleteStaff(id);}

    @GetMapping("/estimate")
    public List<EstimateDto> getEstimateGrid(SearchDto dto) { return estimateService.getEstimateGrid(dto); }

    @PutMapping("/estimate")
    public void saveEstimate(@RequestBody List<EstimateDto> dtos){
        estimateService.saveEstimate(dtos);
    }

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
