package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private RegisteredUser registeredUser;
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private BloodType bloodType;
    private int bloodAmount;
    private String hand;

}
