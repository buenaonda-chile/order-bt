package com.orderbt.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class StaffDto {
    private String id;

    private String password;
    private String passwordKey; //비밀번호 솔트
    private String name;
    private String phoneNum;
    private String email;
    private String activeYn;
    private String memo;
    private LocalDateTime latestDt;
    private LocalDateTime cretDt;
    private LocalDateTime updtDt;
}
