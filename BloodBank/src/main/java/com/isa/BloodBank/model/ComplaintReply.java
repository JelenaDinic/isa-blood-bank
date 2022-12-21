package com.isa.BloodBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity(name="complaintReply")
@Table(name = "complaintReplies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComplaintReply {
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

    private String complaintText;

    private String responseText;

    public ComplaintReply(Complaint complaint) {
        this.center = complaint.getCenter();
        this.complaintText = complaint.getText();
        this.user = complaint.getUser();
        this.staffMember = complaint.getStaffMember();
        this.center = complaint.getCenter();
    }
}
