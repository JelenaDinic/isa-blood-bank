package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.AppointmentReportCreationDTO;
import com.isa.BloodBank.dto.BloodBankCreationDTO;
import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentReport;
import com.isa.BloodBank.model.BloodSupply;
import com.isa.BloodBank.service.AppointmentReportService;
import com.isa.BloodBank.service.AppointmentService;
import com.isa.BloodBank.service.BloodBankCenterService;
import com.isa.BloodBank.service.BloodSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="api/appointment-report")
public class AppointmentReportController {

    private AppointmentReportService service;
    private AppointmentService appointmentService;
    private BloodSupplyService bloodSupplyService;

    @Autowired
    public AppointmentReportController(AppointmentReportService appointmentReportService, AppointmentService appointmentService, BloodSupplyService bloodSupplyService) {
        this.service = appointmentReportService;
        this.appointmentService = appointmentService;
        this.bloodSupplyService = bloodSupplyService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody AppointmentReportCreationDTO appointmentReportDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("Error creating new report!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        AppointmentReport appointmentReport = new AppointmentReport(appointmentReportDTO);
        Appointment a = new Appointment();
        a.setId(appointmentReportDTO.getAppointmentId());
        appointmentReport.setAppointment(a);
        service.create(appointmentReport);
        appointmentService.changeStatusToDone(appointmentReportDTO.getAppointmentId());
        bloodSupplyService.updateAmount(appointmentReport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
