package com.eldopay.companyservice.repository.worker;


import com.eldopay.companyservice.models.handyman.CurrentRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CurrentRepairRepository  extends JpaRepository<CurrentRepair,Long > {

    @Query("SELECT r From Repair r where r.worker.id=:workerId")
    public List<CurrentRepair> getAllWorkerRepair(@Param("workerId") Long workerId);

}