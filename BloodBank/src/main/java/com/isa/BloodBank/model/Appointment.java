package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@Entity(name="appointments")
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private RegisteredUser registeredUser;
//    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
//    private AppointmentReport appointmentReport;
    private LocalDateTime dateTime;
    private int duration;
    private AppointmentStatus status;
}
