package com.eldopay.companyservice.repository.worker;

import com.eldopay.companyservice.models.handyman.CurrentRepair;
import com.eldopay.companyservice.models.handyman.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository  extends JpaRepository<Worker,Long> {
    Optional<Worker> findByName(String username);
    public Worker  findByEmail(String email);

    @Query("SELECT R FROM Repair R WHERE R.status= REQUESTED AND R.worker.id=:driverId")
    public List<CurrentRepair> getAllocatedRepairs(@Param("workerId") Long workerId);

    @Query("SELECT R FROM Repair where R.status=COMPLETED AND R.worker.Id=:workerId")
    public List<CurrentRepair> getCompletedRepairs(@Param("workerId") Long workerId);

}
