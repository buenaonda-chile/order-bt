package com.orderbt.Service;

import com.orderbt.Dto.SearchDto;
import com.orderbt.Dto.StaffDto;

import java.util.List;

public interface StaffService {

    void updateAdminFlag(StaffDto dto);

    List<StaffDto> getStaffGrid(SearchDto dto);

    String dupCheckId(String id);

    void joinStaff(StaffDto dto);

    void deleteStaff(String id);

    void updateStaff(StaffDto dto);

}
