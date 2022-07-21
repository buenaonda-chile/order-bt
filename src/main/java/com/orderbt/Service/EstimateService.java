package com.orderbt.Service;

import com.orderbt.Domain.Estimate;
import com.orderbt.Domain.Item;
import com.orderbt.Domain.Reservation;
import com.orderbt.Dto.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface EstimateService {
    Long createEstimate(EstimateDto dto);

    Reservation createReservation(ReservationDto dto);

    void saveData(OrderDto dto);

    Estimate getEstimate(EstimateDto dto);

    List<Item> getCellModel(ItemDto dto);

    List<Item> getPcm(ItemDto dto);

    List<Item> getCase();

    void sendEmail(MessageDto dto);

    List<EstimateDto> getEstimateGrid(SearchDto dto);

    void saveEstimate(List<EstimateDto> dtos);

    HashMap<String,Object> getEstimateBoard();

    CtnsEstimateDto getCtnsEstimate(Integer estimateId);

    void saveCtnsEstimate(CtnsEstimateDto dto);

    List<CtnsOrderDto> getCtnsOrder(Integer estimateId);

    void saveCtnsOrder(List<CtnsOrderDto> dtos);

    void updateCtnsOrder(List<CtnsOrderDto> dtos);

    void deleteCtnsOrder(List<CtnsOrderDto> dtos);
}
