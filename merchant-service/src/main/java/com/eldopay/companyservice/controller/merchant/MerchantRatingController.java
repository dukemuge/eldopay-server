package com.eldopay.companyservice.controller.merchant;

import com.supeapp.delivery.dtos.requests.MerchantRatingRequest;
import com.supeapp.delivery.dtos.requests.RatingRequest;
import com.supeapp.delivery.exceptions.MerchantException;
import com.supeapp.delivery.exceptions.ProductException;
import com.supeapp.delivery.exceptions.UserException;
import com.supeapp.delivery.models.MerchantRating;
import com.supeapp.delivery.models.Rating;
import com.supeapp.delivery.models.User;
import com.supeapp.delivery.services.JwtService;
import com.supeapp.delivery.services.MerchantRatingService;
import com.supeapp.delivery.services.RatingService;
import com.supeapp.delivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MerchantRatingController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantRatingService merchantRatingService;
    private JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<MerchantRating> createRating(@RequestBody MerchantRatingRequest req,
                                                       @RequestHeader("Authorization")String jwt
    ) throws UserException, ProductException, MerchantException {
        User user = userService.findUserProfileByToken(jwt);
        MerchantRating rating = merchantRatingService.createMerchantRating(req,user.getId());

        return  new ResponseEntity<>(rating, HttpStatus.CREATED);

    }

    @GetMapping("/{merchantId}")
    public ResponseEntity<List<MerchantRating>> getMerchantRatingHandler(
            @PathVariable Long merchantId,
            @RequestHeader("Authorization")String jwt
    ) throws  MerchantException {
        List<MerchantRating> ratings = merchantRatingService.getMerchantRating(merchantId);
        return  new ResponseEntity<>(ratings,HttpStatus.ACCEPTED);
    }



}
