package com.eldopay.companyservice.controller.worker;

import com.supeapp.delivery.dtos.requests.DriverRatingRequest;
import com.supeapp.delivery.dtos.requests.WorkerRatingRequest;
import com.supeapp.delivery.exceptions.DriverException;
import com.supeapp.delivery.exceptions.MerchantException;
import com.supeapp.delivery.exceptions.UserException;
import com.supeapp.delivery.exceptions.WorkerException;
import com.supeapp.delivery.models.DriverRating;
import com.supeapp.delivery.models.User;
import com.supeapp.delivery.models.WorkerRating;
import com.supeapp.delivery.services.DriverRatingService;
import com.supeapp.delivery.services.UserService;
import com.supeapp.delivery.services.WorkerRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class WorkerRatingController {
    private WorkerRatingService workerRatingService;
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<WorkerRating> createWorkerRatingHandler(@RequestBody WorkerRatingRequest req,
                                                                   @RequestHeader("Authorization")String jwt
    ) throws UserException,WorkerException {
        User user = userService.findUserProfileByToken(jwt);
        WorkerRating rating = workerRatingService.createWorkerRating(req,user.getId());

        return  new ResponseEntity<>(rating, HttpStatus.CREATED);

    }

    @GetMapping("/{workerId}")
    public ResponseEntity<List<WorkerRating>> getWorkerRatingHandler(
            @PathVariable Long workerId,
            @RequestHeader("Authorization")String jwt
    ) throws  WorkerException {
        List<WorkerRating> ratings = workerRatingService.getWorkerRating(workerId);
        return  new ResponseEntity<>(ratings,HttpStatus.ACCEPTED);
    }
}
