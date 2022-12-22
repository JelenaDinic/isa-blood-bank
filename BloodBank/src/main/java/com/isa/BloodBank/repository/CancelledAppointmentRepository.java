package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.CancelledAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelledAppointmentRepository extends JpaRepository<CancelledAppointment, Integer> {


}
