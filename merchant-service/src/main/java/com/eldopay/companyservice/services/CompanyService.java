package com.eldopay.companyservice.services;

import com.eldopay.companyservice.dtos.CreateCompanyRequest;
import com.eldopay.companyservice.dtos.county.CreateCountyRequest;
import com.eldopay.companyservice.exceptions.CountyException;
import com.eldopay.companyservice.models.Company;
import com.eldopay.companyservice.models.county.County;
import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.repository.CompanyRepository;

import java.time.LocalDate;
import java.util.Optional;

public class CompanyService {
    private CompanyRepository companyRepository;


    public Company registerCompany(CreateCompanyRequest createCompanyRequest) {
        String encodedPassword = "";
        Company company = new Company();
        company.setBankAccount(createCompanyRequest.getBankAccount());
        company.setEmail(createCompanyRequest.getEmail());
        company.setLogo(createCompanyRequest.getLogo());
        company.setLocation(createCompanyRequest.getLocation());
        company.setTillNumber(createCompanyRequest.getTillNumber());
        company.setDateOfIncorporation(LocalDate.now());
        company.setShortCode(createCompanyRequest.getShortCode());

        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }



}
