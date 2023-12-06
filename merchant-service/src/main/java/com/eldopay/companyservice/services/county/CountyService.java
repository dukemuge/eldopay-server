package com.eldopay.companyservice.services.county;

import com.eldopay.companyservice.dtos.county.CreateCountyRequest;
import com.eldopay.companyservice.dtos.shop.CreateMerchantRequest;
import com.eldopay.companyservice.exceptions.CountyException;
import com.eldopay.companyservice.exceptions.ProductException;
import com.eldopay.companyservice.models.county.County;
import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.models.shop.Product;
import com.eldopay.companyservice.models.wallet.Wallet;
import com.eldopay.companyservice.repository.county.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountyService {
    private CountyRepository countyRepository;


    public County registerCounty(CreateCountyRequest createCountyRequest) {
        Merchant merchant  = new Merchant();
        String encodedPassword = "";
        County county = new County();
        county.setCountyCode(createCountyRequest.getCountyCode());
        county.setCountyLogo(createCountyRequest.getCountyLogo());
        county.setName(createCountyRequest.getName());
        county.setBankName(createCountyRequest.getBankName());
        county.setPayBill(createCountyRequest.getPayBill());
        County savedCounty = countyRepository.save(county);
        return savedCounty;
    }


    public String deleteCounty(Long countyId) throws CountyException {
        County county  = findCountyById(countyId);
        countyRepository.delete(county);
        return "county deleted successfully";
    }

    public County findCountyById(Long id) throws CountyException {
        Optional<County> opt = countyRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new CountyException("product not found with id"+ id);
    }
}
