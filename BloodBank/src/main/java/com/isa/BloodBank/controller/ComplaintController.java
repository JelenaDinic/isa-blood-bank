package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.AppointmentDTO;
import com.isa.BloodBank.dto.ComplaintDisplayDTO;
import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.Complaint;
import com.isa.BloodBank.service.AppointmentService;
import com.isa.BloodBank.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEMADMIN')")
    @GetMapping()
    public ResponseEntity<List<ComplaintDisplayDTO>> getAll() {
        List<Complaint> complaints = service.getAll();
        List<ComplaintDisplayDTO> complaintDisplayDTOS = new ArrayList<>();
        for(Complaint complaint : complaints) {
            if(complaint.isReplied() == false) {
                ComplaintDisplayDTO complaintDisplayDTO = new ComplaintDisplayDTO(complaint);
                complaintDisplayDTOS.add(complaintDisplayDTO);
            }
        }

        return new ResponseEntity<>(complaintDisplayDTOS, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEMADMIN')")
    @PostMapping("/reply/{replyText}/{complaintId}")
    public ResponseEntity<Object> reply(@PathVariable String replyText, @PathVariable int complaintId) {
        try {
            service.reply(replyText, complaintId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
