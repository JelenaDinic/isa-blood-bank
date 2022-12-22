package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="complaints")
@Table(name = "complaints")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private RegisteredUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = true)
    private Staff staffMember;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id", referencedColumnName = "id", nullable = true)
    private BloodBankCenter center;

    private String text;
}
