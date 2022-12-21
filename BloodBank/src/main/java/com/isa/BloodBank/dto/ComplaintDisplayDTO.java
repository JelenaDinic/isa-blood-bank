package com.isa.BloodBank.dto;

import com.isa.BloodBank.model.Complaint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComplaintDisplayDTO {
    private int id;
    private String text;
    private String user;
    private String staffMember;
    private String center;

    public ComplaintDisplayDTO(Complaint complaint) {
        this.id = complaint.getId();
        this.text = complaint.getText();
        this.user = complaint.getUser().getFirstName() + " " + complaint.getUser().getLastName();
        if(complaint.getStaffMember() != null)
            this.staffMember = complaint.getStaffMember().getFirstName() + " " + complaint.getStaffMember().getLastName();
        else
            this.center = complaint.getCenter().getName();

    }
}
