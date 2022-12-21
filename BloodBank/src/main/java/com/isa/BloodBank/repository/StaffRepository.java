package com.isa.BloodBank.repository;
import com.isa.BloodBank.model.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findStaffById(int id);

    Page<Staff> findAll(Pageable pageable);

    Staff findByEmail(String email);
}
