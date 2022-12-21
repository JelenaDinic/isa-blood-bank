package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.AppointmentCalendarEventDTO;
import com.isa.BloodBank.dto.AppointmentDTO;
import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.service.AppointmentService;
import com.isa.BloodBank.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService service;
    private RegisteredUserService userService;

    @Autowired
    public AppointmentController(AppointmentService service, RegisteredUserService registeredUserService) {
        this.service = service;
        this.userService = registeredUserService;
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAll(@PathVariable int id) {
        List<Appointment> appointments = service.findAllByUserId(id);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<AppointmentDTO>();
        for(Appointment a : appointments) {
            AppointmentDTO aDTO = new AppointmentDTO(a);
            appointmentDTOS.add(aDTO);
        }

        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @PostMapping("/penalty")
    public void addPenalty(@RequestBody AppointmentDTO appointmentDTO) {
        userService.addPenalty(appointmentDTO.getUserId());
        service.changeStatusToNonApp(appointmentDTO.getId());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("byBloodBank/{bloodBankId}")
    public ResponseEntity<List<AppointmentCalendarEventDTO>> getAppointmentsByBloodbank(@PathVariable int bloodBankId){
        List<Appointment> appointments = service.findAllByBloodBank(bloodBankId);
        List<AppointmentCalendarEventDTO> appointmentCalendarEventDTOs = new ArrayList<>();
        for(Appointment appointment : appointments) {
            AppointmentCalendarEventDTO appointmentCalendarEventDTO = new AppointmentCalendarEventDTO(appointment);
            appointmentCalendarEventDTOs.add(appointmentCalendarEventDTO);
        }

        return new ResponseEntity<>(appointmentCalendarEventDTOs, HttpStatus.OK);
    }
}
