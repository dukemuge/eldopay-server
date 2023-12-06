package com.eldopay.companyservice.services.uber;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class Calculator {
    private static final int EARTH_RADIUS = 6371;
     public double calculateDistance(double sourceLatitude,double sourceLongitude,
                                     double destLatitude,double destLongitude){
         double diffLatitude = Math.toRadians(destLatitude - sourceLatitude);
         double diffLongitude = Math.toRadians(destLongitude - sourceLatitude );
         double a = Math.sin(diffLatitude/2) * Math.sin(diffLatitude/2)
                 + Math.cos(Math.toRadians(sourceLatitude)) *Math.cos(Math.toRadians(destLatitude))
                 *  Math.sin(diffLongitude/2) * Math.sin(diffLongitude/2);
         double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a ));
         double distance  = EARTH_RADIUS * c;
         return distance;
     }

     public long  calculateTime(LocalDateTime startTime,LocalDateTime endTime){
        Duration duration = Duration.between(startTime,endTime);
        return duration.getSeconds();

    }

    public double calculateFare(double distance){
         double baseFare =11;
         double totalFare  = baseFare * distance ;
         return  totalFare;
    }

}
