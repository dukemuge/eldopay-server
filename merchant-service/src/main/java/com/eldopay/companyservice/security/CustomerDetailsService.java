package com.eldopay.companyservice.security;


import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.repository.CustomerRepository;
import com.supeapp.delivery.models.User;
import com.supeapp.delivery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> userInfo = repository.findByName(username);
        return userInfo.map(CustomerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
