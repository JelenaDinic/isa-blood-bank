package com.isa.BloodBank.repository;
import com.isa.BloodBank.model.RegisteredUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {
    Page<RegisteredUser> findAll(Pageable pageable);
}
