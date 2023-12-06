package com.eldopay.companyservice.controller.uber;

import com.supeapp.delivery.dtos.requests.DriverRatingRequest;
import com.supeapp.delivery.dtos.requests.MerchantRatingRequest;
import com.supeapp.delivery.exceptions.DriverException;
import com.supeapp.delivery.exceptions.MerchantException;
import com.supeapp.delivery.exceptions.ProductException;
import com.supeapp.delivery.exceptions.UserException;
import com.supeapp.delivery.models.DriverRating;
import com.supeapp.delivery.models.MerchantRating;
import com.supeapp.delivery.models.User;
import com.supeapp.delivery.services.DriverRatingService;
import com.supeapp.delivery.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DriverRatingController {
    private DriverRatingService driverRatingService;
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<DriverRating> createDrivingRatingHandler(@RequestBody DriverRatingRequest req,
                                                     @RequestHeader("Authorization")String jwt
    ) throws UserException, DriverException, MerchantException {
        User user = userService.findUserProfileByToken(jwt);
        DriverRating rating = driverRatingService.createDriverRating(req,user.getId());

        return  new ResponseEntity<>(rating, HttpStatus.CREATED);

    }

    @GetMapping("/{driverId}")
    public ResponseEntity<List<DriverRating>> getDriverRatingHandler(
            @PathVariable Long driverId,
            @RequestHeader("Authorization")String jwt
    ) throws MerchantException, DriverException {
        List<DriverRating> ratings = driverRatingService.getDriverRating(driverId);
        return  new ResponseEntity<>(ratings,HttpStatus.ACCEPTED);
    }

}
