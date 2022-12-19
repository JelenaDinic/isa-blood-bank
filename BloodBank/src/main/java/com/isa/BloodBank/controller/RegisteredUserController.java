package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.UserCreationDTO;
import com.isa.BloodBank.dto.UserDisplayDTO;
import com.isa.BloodBank.dto.UserProfileDisplayDTO;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.model.UnregisteredUser;
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

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;

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
            emailSenderService.sendSimpleEmail(newUser.getEmail(), "Please verify your email", "localhost:8082/api/registered-user/codeVerification/"+newUser.getActivationCode());

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/codeVerification/{activationCode}")
    public ResponseEntity<Boolean> codeVerification(@PathVariable("activationCode") String activationCode) throws Exception{
        try {
            System.out.println(activationCode);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Bad activation");
        }
    }



    @GetMapping(path = "/allUsers")
    public ResponseEntity<List<UserDisplayDTO>> findAllUsers(Pageable page) {
        ArrayList<UserDisplayDTO> userDisplayDTOs = (ArrayList<UserDisplayDTO>) service.findAllUsers(page);

        return new ResponseEntity<>(userDisplayDTOs, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
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

}


