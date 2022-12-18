package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.BloodType;
import com.isa.BloodBank.model.RegisteredUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentReportCreationDTO {
    private int id;
    private int registeredUserId;
    private int appointmentId;
    @NotNull
    private String bloodType;
    private int amount;
    @NotNull
    private String hand;
    private double hemoglobinometer;
    @NotNull
    private String copperSulfate;
}
