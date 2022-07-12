package com.orderbt.Controller;

import com.orderbt.Domain.Estimate;
import com.orderbt.Domain.Item;
import com.orderbt.Domain.Order;
import com.orderbt.Domain.Reservation;
import com.orderbt.Dto.EstimateDto;
import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.OrderDto;
import com.orderbt.Dto.ReservationDto;
import com.orderbt.Service.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final EstimateService estimateService;

    @GetMapping("")
    public String getController(){
        return "test";
    }

    @PostMapping("/estimate")
    public Long createEstimate(EstimateDto dto){

        return estimateService.createEstimate(dto);
    }

    @PutMapping("/estimate")
    public void saveData(OrderDto dto) {
        estimateService.saveData(dto);
    }

    @GetMapping("/estimate")
    public Estimate getEstimate(EstimateDto dto){
        System.out.println("cpcpcppcpcpcp");

        return estimateService.getEstimate(dto);
    }

    @PostMapping("/reservation")
    public Reservation createReservation(ReservationDto dto){
        return estimateService.createReservation(dto);
    }

    @GetMapping("/cell_model")
    public List<Item> getCellItem(ItemDto dto) {
        return estimateService.getCellModel(dto);
    }

    @GetMapping("/pcm")
    public List<Item> getPcm(ItemDto dto){
        return estimateService.getPcm(dto);
    }

}
