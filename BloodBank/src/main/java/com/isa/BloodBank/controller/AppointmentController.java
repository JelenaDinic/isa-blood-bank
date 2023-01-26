package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.*;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.repository.AppointmentRepository;
import com.isa.BloodBank.repository.CancelledAppointmentRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.model.RegisteredUser;
import com.isa.BloodBank.service.AppointmentService;
import com.isa.BloodBank.service.EmailSenderService;
import com.isa.BloodBank.service.NewAppointmentService;
import com.isa.BloodBank.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/appointment")
public class AppointmentController {

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private NewAppointmentService newAppointmentService;
    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private AppointmentService service;
    private RegisteredUserService userService;
    @Autowired
    private RegisteredUserRepository userRepository;
    @Autowired
    private CancelledAppointmentRepository cancelledAppointmentRepository;

    @Autowired
    public AppointmentController(AppointmentService service,
                                 RegisteredUserService registeredUserService,
                                 NewAppointmentService newAppointmentService) {
        this.service = service;
        this.userService = registeredUserService;
        this.newAppointmentService = newAppointmentService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAll(@PathVariable int id) {
        List<Appointment> appointments = service.findAllByUserId(id);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<AppointmentDTO>();
        for(Appointment a : appointments) {
            if(a.getStatus() != AppointmentStatus.CANCELLED) {
                AppointmentDTO aDTO = new AppointmentDTO(a);
                appointmentDTOS.add(aDTO);
            }
        }

        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping("/penalty")
    public void addPenalty(@RequestBody AppointmentDTO appointmentDTO) {
        userService.addPenalty(appointmentDTO.getUserId());
        service.changeStatusToNonApp(appointmentDTO.getId());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/scheduleAppointment")
    public ResponseEntity<Object> scheduleAppointment(@RequestBody ScheduleAppointmentDTO dto) throws MessagingException {

        String activationCode = UUID.randomUUID().toString();
        Appointment appointment = service.findById(dto.getId());
        RegisteredUser user = userRepository.findById(dto.getCustomerId());
        List<Appointment> usersAppointments = new ArrayList<>();
        Boolean alreadyCancelled = false;

        alreadyCancelled = service.checkIfAppointmentHasAlreadyBeenCancelled(dto);

        usersAppointments = service.checkIf6MounthPassed(dto);

        if (alreadyCancelled == false) {
            //da li ima popunjenu formu
            if (user.getBloodDonorForms() != null) {
                //slanje mejla za verifikaciju
                for (Appointment a : service.getAll()) {
                    //izaberi termin koji se poslao sa fronta
                    if (a.getId() == dto.getId()) {
                        //da li je izabrani termin u buducnosti
                            //proverava da li ima termina koji su bili pre manje od 6 meseci
                            if (usersAppointments.isEmpty()) {

                                appointment.setActivationQRCode(activationCode);
                                service.changeStatusToPending(appointment);
                                appointment.setRegisteredUser(user);

                                emailSenderService.sendSimpleEmail(user.getEmail(),
                                        "Please verify your appointment",
                                        "http://localhost:8082/api/appointment/QRcodeVerification/" + activationCode);

                                repository.save(appointment);
                                System.out.println(appointment.getActivationQRCode());

                            } else {
                                appointment.setStatus(AppointmentStatus.FREE);
                                return new ResponseEntity<>("It hasn't been 6 months since your last blood donation", HttpStatus.BAD_REQUEST);

                            }
                    }
                }
            } else {
                return new ResponseEntity<>("You don't have filled blood donor form", HttpStatus.BAD_REQUEST);

            }
        }else{
            return new ResponseEntity<>("You can't schedule appointment you once cancelled", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/QRcodeVerification/{activtionQRCode}")
    public ResponseEntity<String> codeVerification(@PathVariable("activtionQRCode") String activtionQRCode) throws Exception{
        try {
            service.codeVerification(activtionQRCode);
            return new ResponseEntity<>("Appointment verified successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Bad activation");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/cancel")
    public void cancelAppointment(@RequestBody ScheduleAppointmentDTO dto) {
        service.cancelAppointment(dto);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("byBloodBank/{bloodBankId}")
    public ResponseEntity<List<AppointmentCalendarEventDTO>> getAppointmentsByBloodbank(@PathVariable int bloodBankId){
        List<Appointment> appointments = service.findAllByBloodBank(bloodBankId);
        List<AppointmentCalendarEventDTO> appointmentCalendarEventDTOs = new ArrayList<>();
        for(Appointment appointment : appointments) {
            if((appointment.getStatus() == AppointmentStatus.IN_FUTURE || appointment.getStatus() == AppointmentStatus.HAPPENED || appointment.getStatus() == AppointmentStatus.FREE) && appointment.getStatus() != AppointmentStatus.CANCELLED) {
                AppointmentCalendarEventDTO appointmentCalendarEventDTO = new AppointmentCalendarEventDTO(appointment);
                appointmentCalendarEventDTOs.add(appointmentCalendarEventDTO);
            }
        }

        return new ResponseEntity<>(appointmentCalendarEventDTOs, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        List<Appointment> appointments = service.getAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getAllForScheduling")
    public ResponseEntity<List<Appointment>> getAllForScheduling() {
        List<Appointment> scheduledAppointments = new ArrayList<>();
        List<Appointment> allAppointments = service.getAll();

        for(Appointment a: allAppointments){
            if(a.getDateTime().isAfter(LocalDateTime.now())){

                if(a.getStatus() == AppointmentStatus.FREE || a.getStatus() == AppointmentStatus.CANCELLED || a.getStatus() == AppointmentStatus.PENDING){
                    scheduledAppointments.add(a);
                }
            }
        }
        return new ResponseEntity<>(scheduledAppointments, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/scheduledAppointmentsForUser/{id}")
    public ResponseEntity<List<Appointment>> getAllScheduledByUserId(@PathVariable int id) {
        List<Appointment> appointments = service.getAllSheduledAppointments(id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/availableNewAppointments/{time}")
    public ResponseEntity<List<Appointment>> getAllAvailableNewAppointments(@PathVariable String time) {
        System.out.println(time);
        LocalDateTime  dateTime = LocalDateTime.parse(time);
        System.out.print("Datetime " + dateTime);
        List<Appointment> appointments = newAppointmentService.getAllAvailableNewAppointments(dateTime);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/scheduleNewAppointment")
    public ResponseEntity<Object> scheduleNewAppointment(@RequestBody NewAppointmentDTO dto) throws MessagingException {
        boolean t = newAppointmentService.scheduleNewAppointment(dto);
        if(t){
            emailSenderService.sendSimpleEmail("dusko.radicic1@gmail.com",
                    "Successfully scheduled appointment",
                    "Your appointment is successfully scheduled.");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("It hasn't been 6 months since your last blood donation", HttpStatus.BAD_REQUEST);
        }
    }



    }
