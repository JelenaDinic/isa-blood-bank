package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.BloodBankCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodBankCenterRepository extends JpaRepository<BloodBankCenter, Integer> {
    BloodBankCenter findBloodBankCenterById(int id);
}
