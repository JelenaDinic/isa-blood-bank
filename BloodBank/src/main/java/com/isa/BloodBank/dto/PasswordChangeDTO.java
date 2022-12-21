package com.isa.BloodBank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordChangeDTO {
    private String newPassword;
    private String confirmNewPassord;
    private int userId;
}
