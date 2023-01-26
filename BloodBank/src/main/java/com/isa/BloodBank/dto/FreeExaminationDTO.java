package com.isa.BloodBank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeExaminationDTO {
    @NotNull
    @NotBlank(message = "Datetime cannot be blank!")
    private String dateTime;
    @NotNull
//    @NotBlank(message = "Duration cannot be blank!")
    private int duration;
    private int staffId;
}
