package com.eldopay.companyservice.repository.uber;

import com.eldopay.companyservice.models.uber.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
