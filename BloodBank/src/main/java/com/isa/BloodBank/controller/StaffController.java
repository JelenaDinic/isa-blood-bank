package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.StaffCreationDTO;
import com.isa.BloodBank.model.Staff;
import com.isa.BloodBank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path="api/staff")
public class StaffController {

    private final StaffService service;

    @Autowired
    public StaffController(StaffService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAll() {
        List<Staff> staff = service.findAll();
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
    @GetMapping("/all-bloodbank-staff/{id}")
    public ResponseEntity<List<Staff>> getAllByCenterId(@PathVariable("id") int id) {
        List<Staff> staff = service.findAllByCenterId(id);
        List<StaffCreationDTO> staffDTOList = new ArrayList<>();
//        for(Staff s: staff) {
//            staffDTOList.add(new StaffCreationDTO(s));
//        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody StaffCreationDTO staffCreationDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.err.println("Error creating new staff!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            service.create(staffCreationDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Staff staff) {
        service.update(staff);
    }
    @GetMapping("/{id}")
    public Optional<Staff> getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

}
