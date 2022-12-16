package com.isa.BloodBank.service.interfaces;

import com.isa.BloodBank.model.Authority;

import java.util.List;

public interface AuthorityServiceInterface {
    List<Authority> findById(Long id);
    List<Authority> findByname(String name);
}
