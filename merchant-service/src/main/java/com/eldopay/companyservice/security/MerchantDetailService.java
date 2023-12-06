package com.eldopay.companyservice.security;


import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.repository.merchant.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class MerchantDetailService implements UserDetailsService {

    @Autowired
    private MerchantRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Merchant> userInfo = repository.findByName(username);
        return userInfo.map(MerchantDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("merchant not found  with" + username));

    }
}
