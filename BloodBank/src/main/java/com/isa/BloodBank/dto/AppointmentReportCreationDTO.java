package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Appointment;
import com.isa.BloodBank.model.BloodType;
import com.isa.BloodBank.model.RegisteredUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Blood type cannot be blank")
    private String bloodType;
    @NotNull
    private int amount;
    @NotNull
    @NotBlank(message = "Hand cannot be blank")
    private String hand;
    @NotNull
    private double hemoglobinometer;
    @NotNull
    @NotBlank(message = "Copper Sulfate cannot be blank")
    private String copperSulfate;
}
