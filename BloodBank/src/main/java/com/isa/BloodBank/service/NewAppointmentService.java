package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.NewAppointmentDTO;
import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class NewAppointmentService {
    private AppointmentService appointmentService;
    private BloodBankCenterService bloodBankCenterService;
    private RegisteredUserService registeredUserService;
    private AppointmentRepository appointmentRepository;

    public NewAppointmentService(AppointmentService appointmentService,
                                 BloodBankCenterService bloodBankCenterService,
                                 RegisteredUserService registeredUserService,
                                 AppointmentRepository appointmentRepository) {
        this.appointmentService = appointmentService;
        this.bloodBankCenterService = bloodBankCenterService;
        this.registeredUserService = registeredUserService;
        this.appointmentRepository = appointmentRepository;
    }

    //Treba popravka
    public List<Appointment> getAllAvailableNewAppointments(LocalDateTime startTime){
        List<Appointment> newAppointments = new ArrayList<>();
        List<Appointment> allAppointments = appointmentService.getAll();
        List<BloodBankCenter> allCenters = bloodBankCenterService.findAll();
        boolean exsistInCenter= false;
        for(BloodBankCenter center: allCenters){
            exsistInCenter= false;
            for(Appointment appointment: allAppointments){
                if(appointment.getBloodBankCenter().getId() == center.getId()){
                    if(startTime.isBefore(appointment.getDateTime().plusMinutes(20))
                            && appointment.getDateTime().isBefore(startTime.plusMinutes(20))
                            && appointment.getStatus()!=AppointmentStatus.FREE){
                        exsistInCenter = true;
                    }
                }
            }
            if(!exsistInCenter){
                Appointment app = new Appointment();
                app.setBloodBankCenter(center);
                app.setDateTime(startTime);
                newAppointments.add(app);
            }

        }
        return newAppointments;
    }

    public boolean scheduleNewAppointment(NewAppointmentDTO dto){
        List<Appointment> usersAppointments = new ArrayList<>();
        //dobavi termine koji su bili pre manje od 6 meseci
        List<Appointment> appointments = appointmentRepository.findAll();
        for (Appointment a : appointments) {
            if (a.getRegisteredUser() != null) {
                if (dto.getPatientId() == a.getRegisteredUser().getId()) {

                    if (a.getDateTime().plusMonths(6).isAfter(dto.getDateTime())) {
                        if (a.getStatus() == AppointmentStatus.HAPPENED) {
                            //termini koji su bili pre manje od 6 meseci
                            usersAppointments.add(a);
                        }
                    }
                }
            }
        }
        if(usersAppointments.isEmpty()){
            Appointment appointment = new Appointment();
            appointment.setDateTime(dto.getDateTime());
            BloodBankCenter center = bloodBankCenterService.findById(dto.getBloodBankCenterId());
            appointment.setBloodBankCenter(center);
            RegisteredUser user = registeredUserService.findById(dto.getPatientId());
            appointment.setRegisteredUser(user);
            appointment.setDuration(30);
            appointment.setStatus(AppointmentStatus.IN_FUTURE);
            appointment.setId(0);
            appointmentRepository.save(appointment);
            return true;
        }else{
            return false;
        }

    }
}
