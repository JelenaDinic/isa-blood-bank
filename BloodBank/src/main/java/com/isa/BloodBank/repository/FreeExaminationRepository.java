package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.FreeExamination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeExaminationRepository extends JpaRepository<FreeExamination, Integer> {
}
