package com.eldopay.companyservice.repository.worker;


import com.eldopay.companyservice.models.handyman.WorkerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRatingRepository  extends JpaRepository<WorkerRating,Long > {

    @Query("SELECT r From WorkerRating r where r.working.id=:workerId")
    public List<WorkerRating> getAllWorkerRating(@Param("workerId") Long workerId);

}

