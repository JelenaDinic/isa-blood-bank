package com.isa.BloodBank.service;

import com.isa.BloodBank.model.Authority;
import com.isa.BloodBank.model.Person;
import com.isa.BloodBank.model.UserRequest;
import com.isa.BloodBank.repository.UserRepository;
import com.isa.BloodBank.service.interfaces.AuthorityServiceInterface;
import com.isa.BloodBank.service.interfaces.UserServiceInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService, UserServiceInterface {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityServiceInterface authService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            return user;
        }
    }

    @Override
    public Person findByEmail(String email) throws UsernameNotFoundException {
        Person u = userRepository.findByEmail(email);
        return u;
    }

    @Override
    public Person findById(int id) throws AccessDeniedException {
        Person u = userRepository.findById(id).orElseGet(null);
        return u;
    }

    public List<Person> findAll() throws AccessDeniedException {
        List<Person> result = userRepository.findAll();
        return result;
    }

    @Override
    public Person save(UserRequest userRequest) {
        Person u = new Person();
        u.setEmail(userRequest.getEmail());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setFirstName(userRequest.getFirstname());
        u.setLastName(userRequest.getLastname());
        u.setEnabled(true);

        List<Authority> auth = authService.findByname("ROLE_USER");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        u.setAuthorities(auth);

        u = this.userRepository.save(u);
        return u;
    }
}
