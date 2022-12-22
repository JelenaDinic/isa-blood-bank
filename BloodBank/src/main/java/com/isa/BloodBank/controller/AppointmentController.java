package com.isa.BloodBank.controller;

import com.isa.BloodBank.dto.AppointmentCalendarEventDTO;
import com.isa.BloodBank.dto.AppointmentDTO;
import com.isa.BloodBank.dto.ScheduleAppointmentDTO;
import com.isa.BloodBank.dto.UserCreationDTO;
import com.isa.BloodBank.model.*;
import com.isa.BloodBank.repository.AppointmentRepository;
import com.isa.BloodBank.repository.CancelledAppointmentRepository;
import com.isa.BloodBank.repository.RegisteredUserRepository;
import com.isa.BloodBank.service.AppointmentService;
import com.isa.BloodBank.service.EmailSenderService;
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
    private AppointmentRepository repository;
    @Autowired
    private AppointmentService service;
    private RegisteredUserService userService;
    @Autowired
    private RegisteredUserRepository userRepository;
    @Autowired
    private CancelledAppointmentRepository cancelledAppointmentRepository;

    @Autowired
    public AppointmentController(AppointmentService service, RegisteredUserService registeredUserService) {
        this.service = service;
        this.userService = registeredUserService;
    }

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


    @PostMapping("/penalty")
    public void addPenalty(@RequestBody AppointmentDTO appointmentDTO) {
        userService.addPenalty(appointmentDTO.getUserId());
        service.changeStatusToNonApp(appointmentDTO.getId());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/scheduleAppointment")
    public void scheduleAppointment(@RequestBody ScheduleAppointmentDTO dto) throws MessagingException {

        String activationCode = UUID.randomUUID().toString();
        Appointment appointment = service.findById(dto.getId());
        RegisteredUser user = userRepository.findById(dto.getCustomerId());
        List<Appointment> usersAppointments = new ArrayList<>();
//        for(CancelledAppointment cancelledAppointment: cancelledAppointmentRepository.findAll()) {
//            if (cancelledAppointment.getAppointmentId() == appointment.getId() && cancelledAppointment.getUserId() == user.getId()) {
//                System.out.println("vec ste otkazali ovaj termin");
//            } else {
                //dobavi termine koji su bili pre manje od 6 meseci
                for(Appointment a : service.getAll())
                {
                    if(a.getRegisteredUser() != null) {
                        if (user.getId() == a.getRegisteredUser().getId()) {

                            if (a.getDateTime().plusMonths(6).isAfter(LocalDateTime.now())) {
                                if (a.getStatus() == AppointmentStatus.HAPPENED) {
                                    //termini koji su bili pre manje od 6 meseci
                                    usersAppointments.add(a);
                                }
                            }
                        }
                    }
                }
                //da li ima popunjenu formu
                if (user.getBloodDonorForms() != null) {
                    //slanje mejla za verifikaciju
                    for (Appointment a : service.getAll()) {
                        //izaberi termin koji se poslao sa fronta
                        if (a.getId() == dto.getId()) {
                            //da li je izabrani termin u buducnosti
                            if (a.getDateTime().isAfter(LocalDateTime.now())) {
                                //proverava da li ima termina koji su bili pre manje od 6 meseci
                                if(usersAppointments.isEmpty()){

                                        appointment.setActivationQRCode(activationCode);
                                        appointment.setStatus(AppointmentStatus.PENDING);
                                        appointment.setRegisteredUser(user);

                                        emailSenderService.sendSimpleEmail(user.getEmail(),
                                                "Please verify your appointment",
                                                "http://localhost:8082/api/appointment/QRcodeVerification/" + activationCode);

            //                            emailSenderService.sendMailWithAttachment(dto.getUserEmail(),
            //                                    "Scan this for verification",
            //                                    "Please verify your appointment", "" +
            //                                            "\"C:\\Users\\vanja\\Desktop\\isa\\projekat pravi bez logina\\frame.png\"");
                                        repository.save(appointment);
                                        System.out.println(appointment.getActivationQRCode());


                                    } else {
                                        appointment.setStatus(AppointmentStatus.FREE);

                                        System.out.println("nije proslo 6 meseci od poslednjeg doniranja");
                                    }

                            } else {
                                appointment.setStatus(AppointmentStatus.FREE);
                                System.out.println("termin je prosao");
                            }
                        }
                    }
                } else{
                    System.out.println("nemate popunjenu formu");
                }
//            }
//        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/QRcodeVerification/{activtionQRCode}")
    public ResponseEntity<String> codeVerification(@PathVariable("activtionQRCode") String activtionQRCode) throws Exception{
        try {
            List<Appointment> allAppointments = service.getAll();
            Appointment scheduledAppointment = new Appointment();

            for(Appointment appointment : allAppointments){
                if(appointment.getActivationQRCode() != null) {
                    if (appointment.getActivationQRCode().equals(activtionQRCode)) {
                        appointment.setIsScheduled(true);
                        appointment.setStatus(AppointmentStatus.IN_FUTURE);

                        System.out.println(appointment.getIsScheduled());
                        repository.save(appointment);
                        scheduledAppointment = appointment;
                    }
                }
            }

            return new ResponseEntity<>("Appointment verified successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Bad activation");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/cancel")
    public void cancelAppointment(@RequestBody ScheduleAppointmentDTO dto) {
        Appointment appointment = service.findById(dto.getId());
        RegisteredUser user = userRepository.findById(dto.getCustomerId());

        CancelledAppointment cancelledAppointment = new CancelledAppointment();


        for (Appointment a: service.getAllSheduledAppointments(dto.getCustomerId())) {
            Boolean isTomorrow = service.checkIfAppointmentIsInLessThan24Hours(a);
            if(a.getRegisteredUser() !=null) {
                if (appointment.getId() == a.getId()) {
                    if (isTomorrow == false) {
                        appointment.setStatus(AppointmentStatus.CANCELLED);
                        appointment.setIsCancelled(true);
                        appointment.setIsScheduled(false);

                        cancelledAppointment.setAppointmentId(appointment.getId());
                        cancelledAppointment.setUserId(appointment.getRegisteredUser().getId());

                        cancelledAppointmentRepository.save(cancelledAppointment);
                        repository.save(appointment);
                    }
                }
            }
        }
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        List<Appointment> appointments = service.getAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/scheduledAppointmentsForUser/{id}")
    public ResponseEntity<List<Appointment>> getAllScheduledByUserId(@PathVariable int id) {
        List<Appointment> appointments = service.getAllSheduledAppointments(id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
