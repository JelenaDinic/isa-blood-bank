package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.BloodSupply;
import com.isa.BloodBank.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BloodSupplyRepository extends JpaRepository<BloodSupply, Integer> {
    @Query("select bs from bloodSupplies bs where bs.bloodType =?1")
    BloodSupply getBloodSupplyByBloodType(BloodType type);
}
