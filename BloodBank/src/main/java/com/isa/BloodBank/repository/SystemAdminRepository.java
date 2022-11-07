package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Integer> {
}
