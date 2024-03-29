package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("select a from appointments a where user_id = ?1 ")
    List<Appointment> findAllByUserId(int id);

    List<Appointment> findAll();
    Appointment findById(int id);
    Appointment findAppointmentById(int id);

    @Query("select a from appointments  a where blood_bank_center_id = ?1 order by date_time asc")
    List<Appointment> findAllByBloodBankId(int id);

}
