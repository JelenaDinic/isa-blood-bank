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
    private LocalDateTime dateTime;
    private int duration;
    private AppointmentStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bloodBankCenter_id", nullable=false)
    private BloodBankCenter bloodBankCenter;

    private String activationQRCode;


    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", registeredUser=" + registeredUser +
                ", dateTime=" + dateTime +
                ", duration=" + duration +
                ", status=" + status +
                ", bloodBankCenter=" + bloodBankCenter +
                ", activationQRCode='" + activationQRCode + '\'' +
                '}';
    }
    @Version
    private Long version;

    public Appointment(int id, AppointmentStatus status) {
        this.id = id;
        this.status = status;

    }
}
