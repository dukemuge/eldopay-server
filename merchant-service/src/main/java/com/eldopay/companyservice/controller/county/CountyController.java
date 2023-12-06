package com.eldopay.companyservice.controller.county;

import com.eldopay.companyservice.dtos.ApiResponse;
import com.eldopay.companyservice.exceptions.CountyException;
import com.eldopay.companyservice.exceptions.ProductException;
import com.eldopay.companyservice.models.county.County;
import com.eldopay.companyservice.models.shop.Product;
import com.eldopay.companyservice.repository.county.CountyRepository;
import com.eldopay.companyservice.services.county.CountyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public class CountyController {
    private CountyService countyService;
    private CountyRepository countyRepository;


    @GetMapping("/id/{countyId}")
    public ResponseEntity<County> getCountyByIdHandler(@PathVariable long countyId) throws CountyException {
        County county = countyService.findCountyById(countyId);
        return  new ResponseEntity<>(county, HttpStatus.ACCEPTED);
    }


    @GetMapping("/counties")
    public ResponseEntity<List<County>>  getAllCountyHandler(){
        List<County> counties = countyRepository.findAll();
        return  new ResponseEntity<>(counties, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{countyId}/delete")
    public ResponseEntity<ApiResponse> deleteCountyHandler(
            @PathVariable Long countyId,@RequestHeader("Authorization") String jwt) throws CountyException {
        countyService.deleteCounty(countyId);
        ApiResponse res = new ApiResponse();
        res.setMessage("county deleted successfully");
        res.setSuccess(true);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }
}