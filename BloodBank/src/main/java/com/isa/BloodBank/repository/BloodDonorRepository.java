package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.BloodDonorForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodDonorRepository extends JpaRepository<BloodDonorForm, Integer> {
}
