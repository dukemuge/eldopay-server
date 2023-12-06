package com.eldopay.companyservice.services.merchant;



import com.eldopay.companyservice.dtos.shop.MerchantRatingRequest;
import com.eldopay.companyservice.exceptions.MerchantException;
import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.models.shop.MerchantRating;
import com.eldopay.companyservice.repository.merchant.MerchantRatingRepository;
import com.eldopay.companyservice.services.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MerchantRatingService {
    private MerchantRatingRepository merchantRatingRepository;
    private MerchantService merchantService;
    private UserService userService;

    public MerchantRating createMerchantRating(MerchantRatingRequest merchantRatingRequest, Long userId) throws MerchantException, UserException {
        Merchant merchant = merchantService.findMerchantById(merchantRatingRequest.getMerchantId());
        User user = userService.findUserById(userId);
        MerchantRating rating = new MerchantRating();
        rating.setCreatedAt(LocalDateTime.now());
        rating.setUser(user);
        rating.setMerchant(merchant);
        return merchantRatingRepository.save(rating);
    }

    public List<MerchantRating> getMerchantRating(Long merchantId) throws MerchantException {
        var  ratings = merchantRatingRepository.findAll().stream()
                .filter(rating -> rating.getMerchant().getId() == merchantId)
                .collect(Collectors.toList());
        return ratings;
    }

    public int getAverageRating(long merchantId){
        var  ratings = merchantRatingRepository.findAll().stream()
                .filter(rating -> rating.getMerchant().getId() == merchantId)
                .collect(Collectors.toList());
        var count  = ratings.stream().count();
        //calculate sum and divide

        return  0;

    }



}
