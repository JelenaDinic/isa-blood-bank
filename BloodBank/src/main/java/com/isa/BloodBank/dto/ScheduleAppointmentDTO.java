package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.AppointmentStatus;
import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.RegisteredUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleAppointmentDTO {
    private int id;
    private LocalDateTime dateTime;
    private int duration;
    private AppointmentStatus status;
    private RegisteredUser user;
    private BloodBankCenter bloodBankCenter;

    public boolean isScheduled;
    public String activationQRCode;

    public ScheduleAppointmentDTO(Appointment appointment) {
        this.setId(appointment.getId());
        this.setDateTime(appointment.getDateTime());
        this.setDuration(appointment.getDuration());
        this.setStatus(appointment.getStatus());
        {
            this.setUser(appointment.getRegisteredUser());
            this.setBloodBankCenter(appointment.getBloodBankCenter());

        }
        this.isScheduled = false;
    }
}
