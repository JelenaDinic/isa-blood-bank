package com.isa.BloodBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.BloodBank.dto.BloodBankCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name="bloodBankCenters")
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;
    private String description;
    private double averageGrade;
    @OneToMany(mappedBy="bloodBankCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Staff> staff;

    public BloodBankCenter(BloodBankCreationDTO bloodBankCreationDTO) {
        this.name = bloodBankCreationDTO.getName();
        this.description = bloodBankCreationDTO.getDescription();
        this.averageGrade = 0.0;
    }
}
