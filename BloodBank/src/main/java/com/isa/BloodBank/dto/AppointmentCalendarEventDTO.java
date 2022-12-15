package com.isa.BloodBank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.BloodBank.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCalendarEventDTO {
    public String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    public LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    public LocalDateTime end;

    public AppointmentCalendarEventDTO(Appointment appointment) {
        LocalDateTime endTime = appointment.getDateTime().plusMinutes(appointment.getDuration());
        String startTimeString = appointment.getDateTime().getHour() + ":" + appointment.getDateTime().getMinute();
        String endTimeString = endTime.getHour() + ":" + endTime.getMinute();
        String user = appointment.getRegisteredUser().getFirstName() + " " + appointment.getRegisteredUser().getLastName();
        this.title = user + "  " + startTimeString + "  -  " + endTimeString;
        this.start = appointment.getDateTime();
        this.end = endTime;
    }
}


