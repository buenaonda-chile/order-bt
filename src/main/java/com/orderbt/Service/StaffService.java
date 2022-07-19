package com.orderbt.Service;

import com.orderbt.Dto.SearchDto;
import com.orderbt.Dto.StaffDto;

import java.util.HashMap;
import java.util.List;

public interface StaffService {

    List<StaffDto> getStaffGrid(SearchDto dto);

    Integer dupCheckId(String id);

    void joinStaff(StaffDto dto);

    void deleteStaff(String id);

    void updateStaff(StaffDto dto);

    void updateStaffActive(List<StaffDto> dtos);

    HashMap<String, Integer> getStaffBoard();
}
