package com.eldopay.companyservice.services.uber;


import com.eldopay.companyservice.dtos.uber.DriverRatingRequest;
import com.eldopay.companyservice.exceptions.DriverException;
import com.eldopay.companyservice.models.uber.Driver;
import com.eldopay.companyservice.models.uber.DriverRating;
import com.eldopay.companyservice.repository.uber.DriverRatingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DriverRatingService {
    private  DriverService  driverService;
    private DriverRatingRepository driverRatingRepository;
    private UserService userService;
    public DriverRating createDriverRating(DriverRatingRequest driverRatingRequest, Long userId) throws MerchantException, UserException, DriverException {
        Driver driver = driverService.findDriverById(driverRatingRequest.getDriverId());
        User user = userService.findUserById(userId);
        DriverRating rating = new DriverRating();
        rating.setCreatedAt(LocalDateTime.now());
        rating.setUser(user);
        rating.setDriver(driver);
        return driverRatingRepository.save(rating);
    }

    public List<DriverRating> getDriverRating(Long driverId) throws DriverException {
        var  ratings = driverRatingRepository.findAll().stream()
                .filter(rating -> rating.getDriver().getId() == driverId)
                .collect(Collectors.toList());
        return ratings;
    }

    public int getAverageRating(long driverId){
        var  ratings = driverRatingRepository.findAll().stream()
                .filter(rating -> rating.getDriver().getId() == driverId)
                .collect(Collectors.toList());
        var count  = ratings.stream().count();
        //calculate sum and divide

        return  0;

    }

}
