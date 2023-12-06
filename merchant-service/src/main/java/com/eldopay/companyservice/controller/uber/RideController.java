package com.eldopay.companyservice.controller.uber;


import com.supeapp.delivery.dtos.RideDTO;
import com.supeapp.delivery.exceptions.DriverException;
import com.supeapp.delivery.exceptions.UserException;
import com.supeapp.delivery.mappers.Mapper;
import com.supeapp.delivery.models.Ride;
import com.supeapp.delivery.models.User;
import com.supeapp.delivery.dtos.requests.RideRequest;
import com.supeapp.delivery.dtos.requests.StartRideRequest;
import com.supeapp.delivery.dtos.response.MessageResponse;
import com.supeapp.delivery.services.DriverService;
import com.supeapp.delivery.services.RideService;
import com.supeapp.delivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RideController {
    @Autowired
    private UserService userService;
    @Autowired
    private DriverService driverService;

    @Autowired
    private RideService rideService;

    @PostMapping("/")
    public ResponseEntity<RideDTO> userRequestRideHandler(
            @RequestBody RideRequest rideRequest,
            @RequestHeader("Authorization") String jwt) throws UserException, DriverException {
        User user = userService.findUserProfileByToken(jwt);
        Ride ride  = rideService.requestRide(rideRequest,user);
        RideDTO rideDTO = Mapper.toRideDTO(ride);
        return  new ResponseEntity<>(rideDTO, HttpStatus.ACCEPTED);
    }

//check this
    @PutMapping("/{rideId}/start")
    public ResponseEntity<MessageResponse>  rideStartHandler(@PathVariable long rideId, @RequestBody StartRideRequest startRideRequest) throws RideException {
        rideService.startRide(rideId, startRideRequest.getOtp());
        MessageResponse res = new MessageResponse("Ride has started");
        return  new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }
    //check this
    @PutMapping("/{rideId}/complete")
    public  ResponseEntity<MessageResponse>  rideCompleteHandler(@PathVariable long rideId){
        MessageResponse messageResponse = new MessageResponse("Ride complete .Thank you for using our app");
    }

    //check this
    @GetMapping("/{rideId}")
    public ResponseEntity<RideDTO> findRideByHandler(@PathVariable long rideId,@RequestHeader("Authorization") String jwt) throws UserException, Ride, RideException {
        User user = userService.findUserProfileByToken(jwt);
        Ride ride = rideService.findRideById(rideId);
        RideDTO rideDTO = Mapper.toRideDTO(ride);
        return  new ResponseEntity<>(rideDTO,HttpStatus.ACCEPTED);
    }

    //check this
    @PutMapping("/{rideId}/accept")
    public ResponseEntity<MessageResponse>  acceptRideHandler(@PathVariable long rideId) throws RideException {
        rideService.acceptRide(rideId);
        MessageResponse res = new MessageResponse("Ride accepted by the driver");
        return  new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/decline")
    public ResponseEntity<MessageResponse> declineRideHandler(@RequestHeader("Authorization")String jwt,
                                                              @PathVariable long rideId) throws DriverException, RideException {
        Driver driver  = driverService.getReqDriverProfile(jwt);
        rideService.declineRide(rideId,driver.getId());

        MessageResponse  response = new MessageResponse("Ride declined by driver");
        return  new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }



}
