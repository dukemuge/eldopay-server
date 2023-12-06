package com.eldopay.companyservice.repository.uber;

import com.eldopay.companyservice.models.uber.Driver;
import com.eldopay.companyservice.models.uber.PassengerRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query("SELECT R FROM Ride R WHERE R.status= REQUESTED AND R.driver.id=:driverId")
            public List<PassengerRide> getAllocatedRides(@Param("driverId") Long driverId);

    @Query("SELECT R FROM R where R.status=COMPLETED AND R.driver.Id=:driverId")
    public List<PassengerRide> getCompletedRides(@Param("driverId") Long driverId);

    public Driver findByEmail(String email);

    Optional<Driver> findByName(String username);
}
