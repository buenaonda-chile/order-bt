package com.orderbt.Service.Impl;

import com.orderbt.Dto.StaffDto;
import com.orderbt.Mapper.LoginMapper;
import com.orderbt.Mapper.StaffMapper;
import com.orderbt.Service.LoginService;
import com.orderbt.Util.Encrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final static long LOGIN_RETENTION_MINUTES = 30;

    private final LoginMapper loginMapper;
    private final StaffMapper staffMapper;

    @Override
    public String getPasswordCheck(StaffDto dto, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        StaffDto regStaff = loginMapper.getPassword(dto);
        String url = null;

        if(regStaff == null){
            url = "login_fail";
        }else{
            String sha512Pwd = Encrypt.setSHA512(dto.getPassword(), regStaff.getPasswordKey());
            if(!regStaff.getId().equals(null) && sha512Pwd.equals(regStaff.getPassword())){ // 비밀번호 일치 하는 경우

                regStaff = staffMapper.getStaffInfo(dto);

                session.setAttribute("staffId", regStaff.getId());

                if( !regStaff.getActiveYn().equals("Y")){
                    url = "login_auth_fail";
                    session.invalidate();
                }else{
                    url = "login";
                }

            }else{  // 비밀번호 불일치
                session.invalidate();
                url = "login_fail";
            }
        }
        return url;
    }

    @Override
    public void autoLogin(String id, HttpServletRequest request) {
        HttpSession session = request.getSession();

        StaffDto staffDto = new StaffDto();
        staffDto.setId(id);
        staffDto = staffMapper.getStaffInfo(staffDto);

        if(staffDto != null){
            session.setAttribute("id", staffDto.getId());
        }
    }

    @Override
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/cms";
    }
}
