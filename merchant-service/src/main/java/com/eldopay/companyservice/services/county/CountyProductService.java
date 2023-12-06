package com.eldopay.companyservice.services.county;

import com.eldopay.companyservice.dtos.county.CreateCountyProductRequest;
import com.eldopay.companyservice.exceptions.CountyProductException;
import com.eldopay.companyservice.models.county.County;
import com.eldopay.companyservice.models.county.CountyProduct;
import com.eldopay.companyservice.models.county.CountyProductCategory;
import com.eldopay.companyservice.repository.county.CountyProductCategoryRepository;
import com.eldopay.companyservice.repository.county.CountyProductRepository;
import com.eldopay.companyservice.repository.county.CountyRepository;
import com.eldopay.companyservice.repository.merchant.CategoryRepository;
import com.eldopay.companyservice.repository.merchant.MerchantRepository;
import com.eldopay.companyservice.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountyProductService {
    private CountyRepository countyRepository;
    private CountyProductRepository countyProductRepository;
    private CountyProductCategoryRepository countyProductCategoryRepository;
    private UserService userService;


    public CountyProduct createCountyProduct(CreateCountyProductRequest req, Long countyId) {
        County county = countyRepository.findById(countyId).get();
        //find the  logged in merchant by Id
        CountyProductCategory topLevel = countyProductCategoryRepository.findByName(req.getTopLevelCategory());
        if (topLevel == null) {
            CountyProductCategory topLevelCategory = new CountyProductCategory();
            topLevelCategory.setLevel(1);
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevel = countyProductCategoryRepository.save(topLevelCategory);
        }
        CountyProductCategory secondLevel = countyProductCategoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());
        if (secondLevel == null) {
            CountyProductCategory secondLevelCategory = new CountyProductCategory();
            secondLevelCategory.setLevel(2);
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setName(req.getSecondLevelCategory());
            secondLevel = countyProductCategoryRepository.save(secondLevelCategory);
        }

        CountyProductCategory thirdLevel = countyProductCategoryRepository.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            CountyProductCategory thirdLevelCategory = new CountyProductCategory();
            thirdLevelCategory.setLevel(3);
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setName(req.getThirdLevelCategory());
            thirdLevel = countyProductCategoryRepository.save(thirdLevelCategory);
        }

        CountyProduct countyProduct = new CountyProduct();
        countyProduct.setCounty(req.getCounty());
        countyProduct.setCountyProductCategory(thirdLevel);


        CountyProduct savedCountyProduct = countyProductRepository.save(countyProduct);

        return savedCountyProduct;
    }


    public CountyProduct updateCountyProduct(Long productId, CountyProduct req) throws CountyProductException {
        CountyProduct product = findCountyProductById(productId);
        return countyProductRepository.save(product);
    }


    public String deleteCountyProduct(Long productId) throws CountyProductException {
        CountyProduct product = findCountyProductById(productId);
        countyProductRepository.delete(product);
        return "county product deleted successfully";
    }

    public CountyProduct findCountyProductById(Long id) throws CountyProductException {
        Optional<CountyProduct> opt = countyProductRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CountyProductException("county product not found with id" + id);
    }

    public List<CountyProduct> findByCountyProductCategory(Long categoryId) {
        List<CountyProduct> products = countyProductRepository.findAll();
        var filteredProducts = products.stream()
                .filter(product -> product.getCountyProductCategory().getId() == categoryId)
                .collect(Collectors.toList());
        return filteredProducts;
    }
}