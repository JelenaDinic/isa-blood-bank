package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.Complaint;
import com.isa.BloodBank.model.ComplaintReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintReplyRepository extends JpaRepository<ComplaintReply, Integer> {
}
