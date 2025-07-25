package com.eldopay.companyservice.security;


;
import com.eldopay.companyservice.models.handyman.Worker;
import com.eldopay.companyservice.repository.worker.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class WorkerDetailsService implements UserDetailsService {

    @Autowired
    private WorkerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Worker> userInfo = repository.findByName(username);
        return userInfo.map(WorkerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}