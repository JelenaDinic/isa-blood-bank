package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.BloodDonorForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BloodDonorRepository extends JpaRepository<BloodDonorForm, Integer> { ;
}
