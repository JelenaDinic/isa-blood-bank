package com.isa.BloodBank.service;

import com.isa.BloodBank.model.Complaint;
import com.isa.BloodBank.repository.ComplaintRepository;
import com.isa.BloodBank.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository repository;

    private final UserRepository userRepository;

    private EmailSenderService emailSenderService;

    public ComplaintService(ComplaintRepository repository,UserRepository userRepository, EmailSenderService emailSenderService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

    public List<Complaint> getAll(){
        return repository.findAll();
    }


    @Transactional
    public void reply(String replyText, int complaintId) throws Exception{
        Complaint complaint = repository.findById(complaintId);
        complaint.setReplied(true);
        complaint.setReplyText(replyText);
        repository.save(complaint);

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

    public Complaint findById(int id) {
        return repository.findById(id);
    }

    public Complaint save(Complaint complaint) {
        Complaint savedComplaint = repository.save(complaint);
        return savedComplaint;
    }
}
