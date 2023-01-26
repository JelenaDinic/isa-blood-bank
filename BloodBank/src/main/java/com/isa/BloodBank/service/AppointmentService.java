package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.NewAppointmentDTO;
import com.isa.BloodBank.dto.ScheduleAppointmentDTO;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.repository.AppointmentRepository;
import com.isa.BloodBank.repository.CancelledAppointmentRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final RegisteredUserRepository repository;

    @Autowired
    private final CancelledAppointmentRepository cancelledAppointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, RegisteredUserRepository repository, CancelledAppointmentRepository cancelledAppointmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.repository = repository;
        this.cancelledAppointmentRepository = cancelledAppointmentRepository;
    }
    public List<Appointment> findAllByUserId(int id) {
        return appointmentRepository.findAllByUserId(id);
    }

    public void changeStatusToDone(int id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        appointment.setStatus(AppointmentStatus.HAPPENED);
        appointmentRepository.save(appointment);
    }
    @Transactional
    public void changeStatusToPending(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.PENDING);
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

    public List<Appointment> getAllSheduledAppointments(int userId){
        List<Appointment> scheduledAppointments = new ArrayList<>();
        List<Appointment> allAppointments = findAllByUserId(userId);

        for(Appointment appointment: allAppointments){
                if (appointment.getStatus() == AppointmentStatus.IN_FUTURE) {
                    scheduledAppointments.add(appointment);
                }
        }

        return scheduledAppointments;
    }

    public Boolean checkIfAppointmentIsInLessThan24Hours(Appointment appointment){

        Boolean lessThan24Hours = false;

        if(LocalDateTime.now().plusHours(24).isAfter(appointment.getDateTime())){
            lessThan24Hours = true;
        }
        else{
            lessThan24Hours = false;
        }
        return lessThan24Hours;

    }


    public List<Appointment> findAllByBloodBank(int id) {
        return appointmentRepository.findAllByBloodBankId(id);

    }

    public Boolean checkIfAppointmentHasAlreadyBeenCancelled(ScheduleAppointmentDTO dto) {
        Boolean alreadyCancelled = false;

        for (CancelledAppointment cancelledAppointment : cancelledAppointmentRepository.findAll()) {

            if (cancelledAppointment != null) {
                if (cancelledAppointment.getAppointmentId() == dto.getId() && cancelledAppointment.getUserId() == dto.getCustomerId()) {
                    System.out.println("vec ste otkazali ovaj termin");
                    alreadyCancelled = true;
                }
            }
        }
        return alreadyCancelled;
    }

    public List<Appointment> checkIf6MounthPassed(ScheduleAppointmentDTO dto) {
        List<Appointment> usersAppointments = new ArrayList<>();

        //dobavi termine koji su bili pre manje od 6 meseci
        for (Appointment a : getAll()) {
            if (a.getRegisteredUser() != null) {
                if (dto.getCustomerId() == a.getRegisteredUser().getId()) {

                    if (a.getDateTime().plusMonths(6).isAfter(LocalDateTime.now())) {
                        if (a.getStatus() == AppointmentStatus.HAPPENED) {
                            //termini koji su bili pre manje od 6 meseci
                            usersAppointments.add(a);
                        }
                    }
                }
            }
        }
        return usersAppointments;
    }
@Transactional
    public void codeVerification(String activtionQRCode){

        for(Appointment appointment : getAll()){
            if(appointment.getActivationQRCode() != null) {
                if (appointment.getActivationQRCode().equals(activtionQRCode)) {
                    appointment.setStatus(AppointmentStatus.IN_FUTURE);

                    appointmentRepository.save(appointment);
                }
            }
        }
    }

    public void cancelAppointment(ScheduleAppointmentDTO dto) {
        for (Appointment a: getAllSheduledAppointments(dto.getCustomerId())) {

            Appointment appointment = findById(dto.getId());
            RegisteredUser user = repository.findById(dto.getCustomerId());
            Boolean isTomorrow = checkIfAppointmentIsInLessThan24Hours(a);
            CancelledAppointment cancelledAppointment = new CancelledAppointment();
            if (isTomorrow == false) {
                appointment.setStatus(AppointmentStatus.CANCELLED);

                cancelledAppointment.setAppointmentId(appointment.getId());
                cancelledAppointment.setUserId(appointment.getRegisteredUser().getId());

                cancelledAppointmentRepository.save(cancelledAppointment);
                appointmentRepository.save(appointment);
            }
        }
//        for (Appointment a: getAllSheduledAppointments(dto.getCustomerId())) {
//            Boolean isTomorrow = checkIfAppointmentIsInLessThan24Hours(a);
//            if(a.getRegisteredUser() !=null) {
//                if (appointment.getId() == a.getId()) {
//                    if (isTomorrow == false) {
//                        appointment.setStatus(AppointmentStatus.CANCELLED);
//
//                        cancelledAppointment.setAppointmentId(appointment.getId());
//                        cancelledAppointment.setUserId(appointment.getRegisteredUser().getId());
//
//                        cancelledAppointmentRepository.save(cancelledAppointment);
//                        appointmentRepository.save(appointment);
//                    }
//                }
//            }
//        }
    }

    public Appointment save(Appointment appointment) {
        Appointment ap = appointmentRepository.save(appointment);
        return ap;
    }

}
