package com.eldopay.companyservice.repository.merchant;


import com.eldopay.companyservice.models.shop.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository  extends JpaRepository<Merchant,Long> {
    public Merchant findByEmail(String email);

    Optional<Merchant> findByName(String username);

}
