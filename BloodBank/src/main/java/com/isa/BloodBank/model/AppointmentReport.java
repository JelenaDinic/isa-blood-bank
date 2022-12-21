package com.isa.BloodBank.model;

import com.isa.BloodBank.dto.AppointmentReportCreationDTO;
import com.isa.BloodBank.dto.UserCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "appointmentReports")
@Table(name = "appointmentReports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private RegisteredUser registeredUser;
    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id", nullable = true)
    private Appointment appointment;
    @NotNull
    private BloodType bloodType;
    private int bloodAmount;
    @NotNull
    private String hand;
    private double hemoglobinometer;
    @NotNull
    private String copperSulfate;

    public AppointmentReport(AppointmentReportCreationDTO dto){
        switch (dto.getBloodType()){
            case "0":
                this.setBloodType(BloodType.AB_POS);
                break;

        }
        this.setBloodAmount(dto.getAmount());
        this.setHand(dto.getHand());
        this.setHemoglobinometer(dto.getHemoglobinometer());
        this.setCopperSulfate(dto.getCopperSulfate());

    }
}
