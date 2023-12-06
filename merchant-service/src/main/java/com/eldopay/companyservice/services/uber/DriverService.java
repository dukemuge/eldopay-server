package com.eldopay.companyservice.services.uber;



import com.eldopay.companyservice.exceptions.DriverException;
import com.eldopay.companyservice.models.uber.Driver;
import com.eldopay.companyservice.models.uber.Vehicle;
import com.eldopay.companyservice.repository.uber.DriverRepository;
import com.eldopay.companyservice.repository.uber.RideRepository;
import com.eldopay.companyservice.repository.uber.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private Calculator distanceCalculation;
//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RideRepository rideRepository;
    public Driver registerDriver(DriverSignupRequest driverSignupRequest) {

        Vehicle createdVehicle = new Vehicle();
        Vehicle vehicle = driverSignupRequest.getVehicle();
        createdVehicle.setId(vehicle.getId());
        createdVehicle.setCapacity(vehicle.getCapacity());
        createdVehicle.setMake(vehicle.getMake());
        createdVehicle.setCarType(vehicle.getCarType());
        createdVehicle.setLicensePlate(vehicle.getLicensePlate());
        createdVehicle.setModel(vehicle.getModel());
        Vehicle savedVehicle = vehicleRepository.save(createdVehicle) ;

        Driver driver  = new Driver();
        String encodedPassword = passwordEncoder.encode(driverSignupRequest.getPassword());
        driver.setLicenseCounty(driver.getLicenseCounty());
        driver.setLicenseNumber(driver.getLicenseNumber());
        driver.setLicenseCounty(driver.getLicenseCounty());
        driver.setFullName(driverSignupRequest.getFullName());
        driver.setEmail(driverSignupRequest.getEmail());
        driver.setMobile(driverSignupRequest.getMobile());
        driver.setPassword(encodedPassword);
        driver.setVehicle(savedVehicle);
        driver.setLatitude(driverSignupRequest.getLatitude());
        driver.setLongitude(driverSignupRequest.getLongitude());
         Driver savedDriver  = driverRepository.save(driver);
        return savedDriver;
    }


    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, double radius, Ride ride) {
        List<Driver> allDrivers = driverRepository.findAll();
        List<Driver> availableDriver = new ArrayList<>();
        for (Driver driver :allDrivers){
            if(driver.getCurrentRide()!= null && driver.getCurrentRide().getStatus()!= RideStatus.COMPLETED){
                continue;
            }
            if(ride.getDeclinedDrivers().contains(driver.getId())){
                continue;
            }
            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();

            double distance = distanceCalculation.calculateDistance(driverLatitude,driverLongitude,
                    pickupLatitude,pickupLongitude);
            availableDriver.add(driver);
        }
        return availableDriver;
    }

    //:TODO streams to be implemented//check the negate logic
//    public List<Driver> getAvailableDrivers2(double pickupLatitude, double pickupLongitude, double radius, Ride ride) {
//        List<Driver> allDrivers = driverRepository.findAll();
//        List<Driver> availableDrivers = allDrivers.stream()
//                .filter(driver ->driver.getCurrentRide() == null && driver.getCurrentRide().getStatus() == RideStatus.COMPLETED )
//                .filter(driver -> ride.getDeclinedDrivers().contains(driver.getId()))
//                .collect(Collectors.toList());
//
//        return availableDrivers;
//    }



    public Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude) {
        double min = Double.MAX_VALUE;
        Driver nearestDriver = null;
        for (Driver driver :availableDrivers){
            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();
            double distance = distanceCalculation.calculateDistance(pickupLatitude,pickupLongitude,driverLatitude,driverLongitude);
            if(min > distance){
                min = distance;
                nearestDriver = driver;
            }
        }
        return nearestDriver;
    }

    public Driver getReqDriverProfile(String jwt) throws DriverException {
        String email = jwtProvider.getEmailFromToken(jwt);
        Driver driver  =driverRepository.findByEmail(email);
        if(driver == null){
            throw new DriverException("driver does not exist with email"+ email);
        }
        return  driver;
    }

    public Ride getDriverCurrentRide(Long driverId) throws DriverException {
        Driver driver =findDriverById(driverId);
        return driver.getCurrentRide();
    }

    public List<Ride> getAllocatedRides(Long id) throws DriverException {
        return driverRepository.getAllocatedRides(id);
    }

    public Driver findDriverById(Long driverId) throws DriverException {
        Optional<Driver> opt =driverRepository.findById(driverId);
        if (opt.isPresent()){
            return opt.get();
        }

        throw new DriverException("driver does not exist with id"+ driverId);
    }

    @Override
    public List<Ride> completedRides(Long driverId) throws DriverException {
        return driverRepository.getCompletedRides(driverId);
    }
}
