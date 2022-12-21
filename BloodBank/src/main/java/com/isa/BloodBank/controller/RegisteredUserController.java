package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.UserCreationDTO;
import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.dto.UserProfileDisplayDTO;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.service.EmailSenderService;
import com.isa.BloodBank.service.RegisteredUserService;
import com.isa.BloodBank.service.UnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping(path="api/registered-user")
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService service;

    @Autowired
    private UnregisteredUserService unregisteredUserService;


    @GetMapping
    public ResponseEntity<List<RegisteredUser>> getAll() {
        List<RegisteredUser> registeredUsers = service.findAll();
        return new ResponseEntity<>(registeredUsers, HttpStatus.OK);
    }

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserCreationDTO userCreationDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            System.err.println("Error creating new user!");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        try {
//            service.create(userCreationDTO);
//            return new ResponseEntity<>(HttpStatus.CREATED);
            //staro
//            RegisteredUser newRegisteredUser = service.create(userCreationDTO);
//            return new ResponseEntity<>(newRegisteredUser, HttpStatus.CREATED);

            //novo(sa verifikacijom)
            UnregisteredUser newUser = unregisteredUserService.create(userCreationDTO);
            emailSenderService.sendSimpleEmail(newUser.getEmail(), "Please verify your email", "http://localhost:8082/api/registered-user/codeVerification/" + newUser.getActivationCode());

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/codeVerification/{activationCode}")
    public ResponseEntity<Boolean> codeVerification(@PathVariable("activationCode") String activationCode) throws Exception{
        try {
            System.out.println(activationCode);
            UnregisteredUser newUser = unregisteredUserService.findByActivationCode(activationCode);
            Person registeredUser = new Person();
            UserCreationDTO dto = new UserCreationDTO();

            dto.setFirstName(newUser.getFirstName());
            dto.setLastName(newUser.getLastName());
            dto.setEmail(newUser.getEmail());
            dto.setPassword(newUser.getPassword());
            dto.setRole(newUser.getRole());
            dto.setDob(newUser.getDob());
            dto.setPhoneNumber(newUser.getPhoneNumber());
            dto.setGender(newUser.getGender());
            dto.setPersonalId(newUser.getPersonalId());

            service.create(dto);
            unregisteredUserService.delete(newUser);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Bad activation");
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping(path = "/allUsers")
    public ResponseEntity<List<UserDisplayDTO>> findAllUsers(Pageable page) {
        ArrayList<UserDisplayDTO> userDisplayDTOs = (ArrayList<UserDisplayDTO>) service.findAllUsers(page);

        return new ResponseEntity<>(userDisplayDTOs, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping(path = "/searchUsers")
    public ResponseEntity<List<UserDisplayDTO>> searchUsers(Pageable page, @RequestParam("searchText") Optional<String> searchText) {
        List<UserDisplayDTO> userDisplayDTOs = service.searchUsers(page, searchText.get());

        return new ResponseEntity<>(userDisplayDTOs, HttpStatus.OK);
    }

    @GetMapping(path="/byEmail/{email}")
    public UserProfileDisplayDTO findByEmail(@PathVariable String email){
        return service.findByEmailDTO(email);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserProfileDisplayDTO> update(@RequestBody UserProfileDisplayDTO userProfileDTO){
        service.update(userProfileDTO);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping(path="/{id}")
    public RegisteredUser getById(@PathVariable("id") int id){
        return service.findById(id);
    }

}


