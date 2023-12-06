package com.eldopay.companyservice.services.uber;



import com.eldopay.companyservice.dtos.uber.RideRequest;
import com.eldopay.companyservice.exceptions.DriverException;
import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.uber.Driver;
import com.eldopay.companyservice.models.uber.PassengerRide;
import com.eldopay.companyservice.repository.NotificationRepository;
import com.eldopay.companyservice.repository.uber.DriverRepository;
import com.eldopay.companyservice.repository.uber.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PassengerRideService{
    @Autowired
    private DriverService driverService;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private Calculator calculator;
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    NotificationRepository notificationRepository;

    public PassengerRide requestRide(RideRequest rideRequest, Customer user) throws DriverException {
        double  pickupLatitude = rideRequest.getPickupLatitude();
        double pickupLongitude = rideRequest.getPickupLongitude();
        double destinationLatitude = rideRequest.getDestinationLatitude();
        double destinationLongitude = rideRequest.getDestinationLongitude();
        String pickupArea = rideRequest.getPickupArea();
        String destinationArea = rideRequest.getDestinationArea();

        PassengerRide existingRide = new PassengerRide();
        List<Driver>   availableDriver = driverService.getAvailableDrivers(pickupLatitude,pickupLongitude,5,existingRide);
        Driver nearestDriver = driverService.findNearestDriver(availableDriver,pickupLatitude,pickupLongitude);
        if(nearestDriver == null){
            throw new DriverException("Driver is not available");
        }

        PassengerRide  ride  = createRideRequest(user,nearestDriver,pickupLatitude,pickupLongitude,destinationLatitude,
                destinationLongitude,pickupArea,destinationArea
        );


        return ride;
    }

    @Override
    public Ride createRideRequest(User user, Driver nearestDriver, double pickupLatitude, double pickupLongitude,
                                  double destinationLatitude, double destinationLongitude,
                                  String pickupArea, String destinationArea) {
      Ride ride  = new Ride();
      ride.setUser(user);
      ride.setPickUpLatitude(pickupLatitude);
      ride.setPickUpLongitude(pickupLongitude);
      ride.setDestinationLatitude(destinationLatitude);
      ride.setDestinationLongitude(destinationLongitude);
      ride.setStatus(RideStatus.REQUESTED);
      ride.setPickupArea(pickupArea);

        return rideRepository.save(ride);
    }

    @Override
    public void acceptRide(Long rideId) throws RideException {
        Ride ride = findRideById(rideId);
        ride.setStatus(RideStatus.ACCEPTED);
        Driver driver = ride.getDriver();
        driver.setCurrentRide(ride);
        Random random = new Random();
        int otp = random.nextInt(9000) + 1000;
        ride.setOtp(otp);
        driverRepository.save(driver);
        rideRepository.save(ride);
        //Continue with notification functionality
        Notification notification = new Notification();
        notification.setRide(ride);
        notification.setTimestamp(LocalDateTime.now());
        notification.setNotificationType(NotificationType.RIDE_CONFIRMATION);
        Notification savedNotification = notificationRepository.save(notification);

    }

    @Override
    public void declineRide(Long rideId,Long driverId) throws RideException {
        Ride ride = new Ride();
//        System.out.println(ride.getId());
        ride.getDeclinedDrivers().add(Math.toIntExact(driverId));
//        System.out.println(ride.getId()+"->" +ride.getDeclinedDrivers());
        List<Driver> availableDrivers = driverService.getAvailableDrivers(ride.getPickUpLatitude(),ride.getPickUpLongitude(),5,ride);
        Driver nearestDriver = driverService.findNearestDriver(availableDrivers,ride.getPickUpLatitude(),ride.getPickUpLongitude());
        ride.setDriver(nearestDriver);
        rideRepository.save(ride);


    }

    @Override
    public void startRide(Long rideId, int otp) throws RideException {
        Ride ride = findRideById(rideId);
        if(otp != ride.getOtp()){
            throw new RideException("please provide a valid otp");
        }
        ride.setStatus(RideStatus.STARTED);
        ride.setStartTime(LocalDateTime.now());
        rideRepository.save(ride);

        Notification notification = new Notification();
//        notification.setUser(setUserride.getUser());
        notification.setMessage("Driver has reached your pickup location");
        notification.setRide(ride);



    }

    @Override
    public void completeRide(Long rideId) throws RideException {
        Ride ride = findRideById(rideId);
        ride.setStatus(RideStatus.COMPLETED);
        ride.setEndTime(LocalDateTime.now());

        double distance  = calculator.calculateDistance(
                ride.getDestinationLatitude(),ride.getDestinationLongitude(),ride.getPickUpLatitude(),ride.getPickUpLongitude()
        );
        LocalDateTime start = ride.getStartTime();
        LocalDateTime end = ride.getEndTime();
        Duration duration = Duration.between(start,end);
        long  millisecond = duration.toMillis();
        System.out.println("duration----------"+ millisecond);

        double fare = calculator.calculateFare(distance);
        ride.setDistance(Math.round(distance *100)/100.0);
        ride.setFare(BigDecimal.valueOf(Math.round(fare)));
        ride.setDuration(millisecond);
        ride.setEndTime(LocalDateTime.now());

        Driver driver = ride.getDriver();
        driver.getRides().add(ride);
        driver.setCurrentRide(null);

        //80 percent tom driver and 20 to the company
        BigDecimal driverRevenue = driver.getTotalRevenue().add(BigDecimal.valueOf(Math.round(fare * 0.8)));
        driver.setTotalRevenue(driverRevenue);
        rideRepository.save(ride);

    }

    @Override
    public void cancelRide(Long rideId) throws RideException {
        Ride ride = findRideById(rideId);
        ride.setStatus(RideStatus.CANCELLED);
        rideRepository.save(ride);

    }

    @Override
    public Ride findRideById(Long rideId) throws RideException {
        Optional<Ride> ride = rideRepository.findById(rideId);
        if (ride.isPresent()){
            return  ride.get();
        }
        throw new  RideException("ride does not exist with id" + rideId);
    }
}
