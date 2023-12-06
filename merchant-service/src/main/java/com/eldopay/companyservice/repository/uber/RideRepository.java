package com.eldopay.companyservice.repository.uber;

import com.eldopay.companyservice.models.uber.PassengerRide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<PassengerRide,Long> {
}
