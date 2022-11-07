package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bloodBankCenters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BloodBankCenter {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private String description;
    private double averageGrade;
    @OneToMany(mappedBy="bloodBankCenter", cascade = CascadeType.ALL)
    private Set<Staff> staff;
}
