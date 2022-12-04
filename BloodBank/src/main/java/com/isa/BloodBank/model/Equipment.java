package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="equipments")
@Table(name = "equipments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bloodBankCenter_id", nullable=false)
    private BloodBankCenter bloodBankCenter;

}
