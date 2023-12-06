package com.eldopay.companyservice.security;


import com.eldopay.companyservice.models.uber.Driver;
import com.eldopay.companyservice.repository.uber.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class DriverDetailsService implements UserDetailsService {

    @Autowired
    private DriverRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Driver> userInfo = repository.findByName(username);
        return userInfo.map(DriverDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("driver not found  with" + username));

    }
}
