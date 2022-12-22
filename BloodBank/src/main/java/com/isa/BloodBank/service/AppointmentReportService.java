package com.isa.BloodBank.service;

import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.model.Address;
import com.isa.BloodBank.model.AppointmentReport;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.repository.AppointmentReportRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AppointmentReportService {
    private final AppointmentReportRepository appointmentReportRepository;

    public AppointmentReportService(AppointmentReportRepository appointmentReportRepository) {
        this.appointmentReportRepository = appointmentReportRepository;
    }

    public void create(AppointmentReport report){
        try {
            appointmentReportRepository.save(report);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Can't save appointment report");
        }

    }
}
