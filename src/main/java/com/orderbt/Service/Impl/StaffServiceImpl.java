package com.orderbt.Service.Impl;

import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.SearchDto;
import com.orderbt.Dto.StaffDto;
import com.orderbt.Mapper.StaffMapper;
import com.orderbt.Service.StaffService;
import com.orderbt.Util.Encrypt;
import com.orderbt.Util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;

    @Override
    @Transactional(readOnly = true)
    public List<StaffDto> getStaffGrid(SearchDto dto) {

        if(dto.getKeyword() != null)
            dto.setKeywordList(Util.makeForeach(dto.getKeyword(), ","));

        return staffMapper.getStaffGrid(dto);
    }

    @Override
    public Integer dupCheckId(String id) {
        return staffMapper.dupCheckId(id);
    }

    @Override
    @Transactional
    public void joinStaff(StaffDto dto) {
        // salt + SHA512 암호화 적용
        String password = dto.getPassword();
        String password_key = Encrypt.getSaltKey();
        dto.setPassword(Encrypt.setSHA512(password, password_key));
        dto.setPasswordKey(password_key);

        staffMapper.joinStaff(dto);
    }

    @Override
    @Transactional
    public void deleteStaff(String id) {
        staffMapper.deleteStaff(id);
    }

    @Override
    @Transactional
    public void updateStaff(StaffDto dto) {
        String password = dto.getPassword();
        if(password != ""){
            dto.setPasswordKey(staffMapper.getPassword(dto));
            String shaPwd = Encrypt.setSHA512(password, dto.getPasswordKey());
            dto.setPassword(shaPwd);
        }
        staffMapper.updateStaff(dto);
    }

    @Override
    public void updateStaffActive(List<StaffDto> dtos) {
        for(StaffDto dto : dtos){
            staffMapper.updateStaffActive(dto);
        }
    }

    @Override
    public HashMap<String, Integer> getStaffBoard() {
        return staffMapper.getStaffBoard();
    }
}
