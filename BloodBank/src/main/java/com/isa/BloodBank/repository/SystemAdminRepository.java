package com.isa.BloodBank.repository;
import com.isa.BloodBank.model.SystemAdmin;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Integer> {
    Page<SystemAdmin> findAll(Pageable pageable);
}
