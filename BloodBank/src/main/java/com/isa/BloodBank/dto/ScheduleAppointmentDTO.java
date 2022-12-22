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
    private int customerId;

    public ScheduleAppointmentDTO(Appointment appointment) {
        this.setId(appointment.getId());
        this.setCustomerId(appointment.getRegisteredUser().getId());

    }
}
