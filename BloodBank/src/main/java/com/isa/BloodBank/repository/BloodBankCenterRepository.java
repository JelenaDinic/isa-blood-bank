package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.BloodBankCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankCenterRepository extends JpaRepository<BloodBankCenter, Integer> {
}
