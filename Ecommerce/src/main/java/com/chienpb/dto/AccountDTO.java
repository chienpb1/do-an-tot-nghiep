package com.chienpb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String photo;
    private String role;
}
