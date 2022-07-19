package com.orderbt.Mapper;

import com.orderbt.Dto.StaffDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LoginMapper {
    public StaffDto getPassword(StaffDto dto);

    public void updateLoginTime(StaffDto vo);
}
