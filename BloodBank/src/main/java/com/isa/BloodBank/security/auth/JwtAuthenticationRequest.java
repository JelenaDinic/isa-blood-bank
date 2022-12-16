package com.isa.BloodBank.security.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// DTO za login
public class JwtAuthenticationRequest {

    private String email;
    private String password;



}