package com.eldopay.companyservice.controller.worker;

import com.supeapp.delivery.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;
    @GetMapping("/profile")
    public ResponseEntity<Worker> getReqDriverProfileHandler(@RequestHeader("Authorization") String jwt) throws DriverException {
        Worker worker = driverService.getReqDriverProfile(jwt);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }
    @GetMapping("/{driverId}/current_ride")
    public ResponseEntity<Ride> getDriversCurrentRideHandler(@PathVariable Long driverId) throws DriverException {
        Ride ride = driverService.getDriverCurrentRide(driverId);
        return new ResponseEntity<>(ride,HttpStatus.ACCEPTED);
    }

    @GetMapping("/rides/completed")
    public ResponseEntity<List<Ride>> getCompletedRidesHandler(@RequestHeader("Authorization") String jwt) throws DriverException {
        Driver driver  =driverService.getReqDriverProfile(jwt);
        List<Ride> rides = driverService.completedRides(driver.getId());
        return new ResponseEntity<>(rides,HttpStatus.OK);
    }
    @GetMapping("/{driverId}/allocated")
    public ResponseEntity<List<Ride>> getAllocatedRidesHandler(@PathVariable Long driverId) throws DriverException {
        List<Ride> rides = driverService.getAllocatedRides(driverId);
        return new ResponseEntity<>(rides,HttpStatus.ACCEPTED);
    }

}
