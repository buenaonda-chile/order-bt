package com.orderbt.Service;

import com.orderbt.Dto.StaffDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    public String getPasswordCheck(StaffDto dto, HttpServletRequest request, HttpServletResponse response);

    public void autoLogin(String id, HttpServletRequest request);

    public String logOut(HttpServletRequest request);
}
