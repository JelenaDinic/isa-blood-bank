package com.isa.BloodBank.service;

import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.SystemAdmin;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import com.isa.BloodBank.repository.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAdminService {

    private final SystemAdminRepository repository;

    @Autowired
    public SystemAdminService(SystemAdminRepository repository) {
        this.repository = repository;
    }

    public SystemAdmin create(SystemAdmin systemAdmin) {
        return repository.save(systemAdmin);
    }

    public List<SystemAdmin> findAll() {
        return repository.findAll();
    }

}
