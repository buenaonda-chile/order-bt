package com.orderbt.Mapper;

import com.orderbt.Domain.Estimate;
import com.orderbt.Dto.EstimateDto;
import com.orderbt.Dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface EstimateMapper {
    List<EstimateDto> getEstimateGrid(SearchDto dto);

    void saveEstimate(EstimateDto dto);

    HashMap<String,Object> getEstimateBoard();
}
