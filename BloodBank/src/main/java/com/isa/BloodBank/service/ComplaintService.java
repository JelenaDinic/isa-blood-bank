package com.isa.BloodBank.service;

import com.isa.BloodBank.model.Complaint;
import com.isa.BloodBank.model.ComplaintReply;
import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.repository.ComplaintReplyRepository;
import com.isa.BloodBank.repository.ComplaintRepository;
import com.isa.BloodBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository repository;
    private final ComplaintReplyRepository complaintReplyRepository;

    private final UserRepository userRepository;

    private EmailSenderService emailSenderService;

    public ComplaintService(ComplaintRepository repository, ComplaintReplyRepository complaintReplyRepository, UserRepository userRepository, EmailSenderService emailSenderService) {
        this.repository = repository;
        this.complaintReplyRepository = complaintReplyRepository;
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

    public List<Complaint> getAll(){
        return repository.findAll();
    }

    public void reply(String replyText, int complaintId) {
        Complaint complaint = repository.findById(complaintId);

        ComplaintReply reply = new ComplaintReply(complaint);
        reply.setResponseText(replyText);

        repository.delete(complaint);
        complaintReplyRepository.save(reply);

        String userEmail = complaint.getUser().getEmail();
        String regarding;
        if(complaint.getStaffMember() != null ) {
            regarding = complaint.getStaffMember().getFirstName() + " " + complaint.getStaffMember().getLastName();
        }
        else {
            regarding = complaint.getCenter().getName();
        }
        emailSenderService.sendSimpleEmail(userEmail, "Odgovor na Vasu zalbu na " + regarding, replyText);
    }
}
