package com.eldopay.companyservice.controller.merchant;


import com.eldopay.companyservice.dtos.shop.RatingRequest;
import com.eldopay.companyservice.exceptions.ProductException;
import com.eldopay.companyservice.exceptions.UserException;
import com.eldopay.companyservice.models.shop.Rating;
import com.eldopay.companyservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/ratings")
@RestController
public class RatingController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating>  createRating(@RequestBody RatingRequest req,
                                                @RequestHeader("Authorization")String jwt
    ) throws UserException, ProductException {
        User user = userService.findUserProfileByToken(jwt);
        Rating rating = ratingService.createRating(req,user);

        return  new ResponseEntity<>(rating, HttpStatus.CREATED);

    }

    @GetMapping("product/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(
            @PathVariable Long productId,
            @RequestHeader("Authorization")String jwt
    ) throws UserException, ProductException {
        User user =userService.findUserProfileByToken(jwt);
        List<Rating>  ratings = ratingService.getProductRating(productId);

return  new ResponseEntity<>(ratings,HttpStatus.ACCEPTED);
    }




}
