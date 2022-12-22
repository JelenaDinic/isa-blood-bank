package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    Complaint findById(int id);
}
