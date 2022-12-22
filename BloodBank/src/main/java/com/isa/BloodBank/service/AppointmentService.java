package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.ScheduleAppointmentDTO;
import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.repository.AppointmentRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    }

    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }

//    public List<ScheduleAppointmentDTO> getAllDTOs(){
//        List<ScheduleAppointmentDTO> dtos = new ArrayList<>();
//        List<Appointment> appointments =  appointmentRepository.findAll();
//
//        for(Appointment appointment : appointments){
//            ScheduleAppointmentDTO dto = new ScheduleAppointmentDTO(appointment);
//            dtos.add(dto);
//        }
//
//        return dtos;
//    }

    public List<Appointment> getAllSheduledAppointments(int userId){
        List<Appointment> scheduledAppointments = new ArrayList<>();
        List<Appointment> allAppointments = findAllByUserId(userId);

        for(Appointment appointment: allAppointments){
            if(appointment.getIsScheduled() != null) {
                if (appointment.getIsScheduled() == true) {
                    scheduledAppointments.add(appointment);
                }
            }
        }

        return scheduledAppointments;
    }

    public Boolean checkIfAppointmentIsInLessThan24Hours(Appointment appointment){

        if(LocalDateTime.now().plusHours(24).isAfter(appointment.getDateTime())){
            appointment.setLessThan24hours(true);
        }
        else{
            appointment.setLessThan24hours(false);
        }
        return appointment.getLessThan24hours();

    }


    public List<Appointment> findAllByBloodBank(int id) {
        return appointmentRepository.findAllByBloodBankId(id);

    }
}
