package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Equipment;
import com.isa.BloodBank.model.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Query("select e from equipment e where e.type =?1")
    Equipment getEquipmentByType(EquipmentType type);
}
