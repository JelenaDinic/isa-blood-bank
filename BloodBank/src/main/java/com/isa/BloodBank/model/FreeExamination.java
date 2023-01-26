package com.isa.BloodBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.BloodBank.dto.FreeExaminationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="freeExaminations")
@Table(name = "freeExaminations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FreeExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime dateTime;
    private int duration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="staff_id", referencedColumnName = "id", nullable= true)
    private Staff staff;

    public FreeExamination(FreeExaminationDTO dto, Staff staff) {
        setDateTime(LocalDateTime.parse(dto.getDateTime()));
        setDuration(dto.getDuration());
        setStaff(staff);
    }

    public FreeExamination(int id) {
        super();
        this.id = id;
    }
}

