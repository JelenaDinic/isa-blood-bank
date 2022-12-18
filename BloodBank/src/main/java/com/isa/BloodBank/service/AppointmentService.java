package com.isa.BloodBank.service;

import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.repository.AppointmentRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final RegisteredUserRepository repository;

    public AppointmentService(AppointmentRepository appointmentRepository, RegisteredUserRepository repository) {
        this.appointmentRepository = appointmentRepository;
        this.repository = repository;
    }
    public List<Appointment> findAllByUserId(int id) {
        return appointmentRepository.findAllByUserId(id);
    }

    public void changeStatusToDone(int id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        appointment.setStatus(AppointmentStatus.HAPPENED);
        appointmentRepository.save(appointment);
    }
    public void changeStatusToNonApp(int id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        appointment.setStatus(AppointmentStatus.NON_APPEARANCE);
        appointmentRepository.save(appointment);
    }
    public Appointment findById(int id) {
        return appointmentRepository.findAppointmentById(id);


    public List<Appointment> findAllByBloodBank(int id) {
        return appointmentRepository.findAllByBloodBankId(id);

    }
}
