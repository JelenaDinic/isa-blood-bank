package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.NewAppointmentDTO;
import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.model.BloodBankCenter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class NewAppointmentService {
    private AppointmentService appointmentService;
    private BloodBankCenterService bloodBankCenterService;

    public NewAppointmentService(AppointmentService appointmentService,
                                 BloodBankCenterService bloodBankCenterService) {
        this.appointmentService = appointmentService;
        this.bloodBankCenterService = bloodBankCenterService;
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
}
