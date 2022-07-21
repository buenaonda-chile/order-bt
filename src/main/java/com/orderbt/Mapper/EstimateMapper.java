package com.orderbt.Mapper;

import com.orderbt.Domain.Estimate;
import com.orderbt.Dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface EstimateMapper {
    List<EstimateDto> getEstimateGrid(SearchDto dto);

    void saveEstimate(EstimateDto dto);

    HashMap<String,Object> getEstimateBoard();

    CtnsEstimateDto getCtnsEstimate(Integer estimateID);

    void saveCtnsEstimate(CtnsEstimateDto dto);

    List<CtnsOrderDto> getCtnsOrder(Integer estimateId);

    void saveCtnsOrder(CtnsOrderDto dto);

    void updateCtnsOrder(CtnsOrderDto dto);

    void deleteCtnsOrder(CtnsOrderDto dto);
}
