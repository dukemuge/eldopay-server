package com.eldopay.companyservice.controller.merchant;


import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.repository.merchant.MerchantRepository;
import com.eldopay.companyservice.services.merchant.MerchantService;
import com.eldopay.companyservice.services.merchant.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MerchantController {
    @Autowired
    private OrderService orderService;

    private MerchantService merchantService;

    private MerchantRepository merchantRepository;

    private PasswordEncoder  passwordEncoder;


    //register the Merchant



    @GetMapping("/agrovet/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestAgrovetAgent(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByAgrovet(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }

    @GetMapping("/hardware/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestHardwareAgent(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByHardware(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }

    @GetMapping("/gas/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestGasAgent(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByGas(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }

    @GetMapping("/gift/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestGiftAgent(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByGift(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }

    @GetMapping("/grocery/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestHardware(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByGrocery(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }


    @GetMapping("/hotel/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestHotel(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByHotel(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }


    @GetMapping("/pharmacy/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestPharmacyAgent(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByPharmacy(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }

    @GetMapping("/shop/{county}/all")
    public ResponseEntity<List<Merchant>> getAllNearestShopAgent(@PathVariable  String county){
        List<Merchant> merchants = merchantService.findByShop(county);
        return  new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }














}
