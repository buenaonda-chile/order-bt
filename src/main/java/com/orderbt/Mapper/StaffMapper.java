package com.orderbt.Mapper;

import com.orderbt.Dto.SearchDto;
import com.orderbt.Dto.StaffDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface StaffMapper {
    void updateStaffActive(StaffDto dto);

    List<StaffDto> getStaffGrid(SearchDto dto);

    Integer dupCheckId(String id);

    void joinStaff(StaffDto dto);

    String getPassword(StaffDto dto);

    void deleteStaff(String id);

    void updateStaff(StaffDto dto);

    StaffDto getStaffInfo(StaffDto dto);

    HashMap<String, Integer> getStaffBoard();
}
