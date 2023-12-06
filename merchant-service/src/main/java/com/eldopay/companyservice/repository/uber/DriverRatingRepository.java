package com.eldopay.companyservice.repository.uber;

import com.eldopay.companyservice.models.uber.DriverRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRatingRepository  extends JpaRepository<DriverRating,Long > {

    @Query("SELECT r FROM DriverRating r where r.driver.id=:driverId")
    public List<DriverRating> getAllDriverRating(@Param("driverId") Long driverId);

}

