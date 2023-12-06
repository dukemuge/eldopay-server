package com.eldopay.companyservice.services.merchant;


//import org.springframework.security.crypto.password.PasswordEncoder;
import com.eldopay.companyservice.dtos.shop.CreateMerchantRequest;
import com.eldopay.companyservice.exceptions.MerchantException;
import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.models.shop.MerchantType;
import com.eldopay.companyservice.models.shop.Product;
import com.eldopay.companyservice.models.wallet.Wallet;
import com.eldopay.companyservice.repository.merchant.MerchantRepository;
import com.eldopay.companyservice.repository.merchant.ProductRepository;
import com.eldopay.companyservice.repository.wallet.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MerchantService {
    private MerchantRepository merchantRepository;
    private WalletRepository walletRepository;
//    private PasswordEncoder passwordEncoder;

//    private GeoLocationService geoLocationService;


    private ProductRepository productRepository;




    public Merchant registerMerchant(CreateMerchantRequest createMerchantRequest) {
        Merchant merchant  = new Merchant();
        String encodedPassword = "";

//        String encodedPassword = passwordEncoder.encode(createMerchantRequest.getPassword());
        merchant.setId(createMerchantRequest.getId());
        merchant.setCountyLicense(merchant.getCountyLicense());
        merchant.setMobileNo(createMerchantRequest.getMobileNo());
        merchant.setUserName(createMerchantRequest.getUserName());
        merchant.setAddress(createMerchantRequest.getAddress());
        merchant.setApproved(false);
        merchant.setSecondName(createMerchantRequest.getSecondName());
        merchant.setFirstName(createMerchantRequest.getFirstName());
//        merchant.setMerchantId(createMerchantRequesttRequest.getNationalId());
        merchant.setPassword(encodedPassword);
        merchant.setLocation(createMerchantRequest.getLocation());
        //:TODO IMPLEMENT A 6 DIGIT NO APLHANUMERIC NUMBER
        merchant.setMerchantNumber(String.valueOf(Math.random()));
        merchant.setCounty(createMerchantRequest.getCounty());
        merchant.setShopName(createMerchantRequest.getShopName());
        merchant.setShopLogo(createMerchantRequest.getShopLogo());
        merchant.setStreetLocation(createMerchantRequest.getStreetLocation());
        merchant.setRoles(assignRole(createMerchantRequest.getMerchantType()));
        merchant.setSourceLatitude(createMerchantRequest.getSourceLatitude());
        merchant.setSourceLongitude(createMerchantRequest.getSourceLongitude());
        merchant.setMerchantType(createMerchantRequest.getMerchantType());


        Merchant savedMerchant  = merchantRepository.save(merchant);
        Wallet wallet  = new Wallet();
        wallet.setMerchant(savedMerchant);
        walletRepository.save(wallet);
        return savedMerchant;
    }

    public String   approveMerchantAccount(Long merchantId){
        Merchant merchant = merchantRepository.findById(merchantId).get();
        merchant.setApproved(true);
        return "merchant account successfully approved";
    }


//Find a good criteria once online
    public List<Merchant> findNearestMerchantByCounty(String county){
                var merchants = merchantRepository.findAll().stream().
                        filter(merchant -> merchant.getCounty() == county)
                        .collect(Collectors.toList());

        return  merchants;
    }

    public List<Merchant> findByAgrovet(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.AGROVET )
                .collect(Collectors.toList());
        return merchants;
    }


    public List<Merchant> findByGas(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.GAS )
                .collect(Collectors.toList());
        return merchants;
    }

    public List<Merchant> findByGift(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.GIFT )
                .collect(Collectors.toList());
        return merchants;
    }



    public List<Merchant> findByClothing(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.CLOTHING )
                .collect(Collectors.toList());
        return merchants;
    }


    public List<Merchant> findByGrocery(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.GROCERY )
                .collect(Collectors.toList());
        return merchants;
    }



    public List<Merchant> findByHotel(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.HOTEL )
                .collect(Collectors.toList());
        return merchants;
    }
    public List<Merchant> findByHardware(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.HARDWARE )
                .collect(Collectors.toList());
        return merchants;
    }


    public List<Product> findByProductByMerchant(long merchantId){
        Merchant merchant = merchantRepository.findById(merchantId).get();
        var products = productRepository.findAll().stream()
                .filter(product -> product.getMerchant() == merchant)
                .collect(Collectors.toList());
        return products;
    }

    public List<Merchant> findByPharmacy(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.PHARMACY )
                .collect(Collectors.toList());
        return merchants;
    }


    public List<Merchant> findByShop(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.SHOP )
                .collect(Collectors.toList());
        return merchants;
    }



    public List<Merchant> findBySupermarket(String county){
        var merchants = merchantRepository.findAll().stream().
                filter(merchant -> merchant.getCounty() == county)
                .filter(merchant -> merchant.getMerchantType() == MerchantType.SUPERMARKET )
                .collect(Collectors.toList());
        return merchants;
    }


    public String assignRole(MerchantType merchantType){
        var roles = "";
        if(merchantType.equals(MerchantType.AGROVET)){
            return "AGROVET";
        }
        if(merchantType.equals(MerchantType.GAS)){
            return "GAS";
        }
        if(merchantType.equals(MerchantType.GIFT)){
            return "GIFT";
        }
        if(merchantType.equals(MerchantType.CLOTHING)){
            return "CLOTHING";
        }
        if(merchantType.equals(MerchantType.GROCERY)){
            return "GROCERY";
        }
        if(merchantType.equals(MerchantType.HARDWARE)){
            return "HARDWARE";
        }
        if(merchantType.equals(MerchantType.HOTEL)){
            return "HOTEL";
        }
        if(merchantType.equals(MerchantType.PHARMACY)){
            return "PHARMACY";
        }

        if(merchantType.equals(MerchantType.SHOP)){
            return "SHOP";
        }

        //:TODO Add more roles based on screens

        return  roles;
    }

    public Merchant findMerchantById(Long id) throws MerchantException {
        Optional<Merchant> merchant =merchantRepository.findById(id);

        if(merchant.isPresent()){
            return merchant.get();
        }
        throw new MerchantException("merchant not found with id --"+id);
    }

}
