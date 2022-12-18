package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private int id;
    private LocalDateTime dateTime;
    private int duration;
    private String status;
    private int userId;
    private String user;

    public AppointmentDTO(Appointment appointment) {
        this.setId(appointment.getId());
        this.setDateTime(appointment.getDateTime());
        this.setDuration(appointment.getDuration());
        switch (appointment.getStatus()){
            case HAPPENED:
                this.setStatus("Donation done");
                break;
            case IN_FUTURE:
                this.setStatus("Possible blood donor");
                break;
            case NON_APPEARANCE:
                this.setStatus("Non appearance");
                break;
            default:
                this.setStatus("Not allowed");


        }
        this.setUser(appointment.getRegisteredUser().getFirstName() + " " + appointment.getRegisteredUser().getLastName());
        this.setUserId(appointment.getRegisteredUser().getId());
    }
}
