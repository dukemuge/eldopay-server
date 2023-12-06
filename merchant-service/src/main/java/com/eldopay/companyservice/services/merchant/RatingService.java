package com.eldopay.companyservice.services.merchant;


import com.eldopay.companyservice.dtos.shop.RatingRequest;
import com.eldopay.companyservice.exceptions.ProductException;
import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.shop.Product;
import com.eldopay.companyservice.models.shop.Rating;
import com.eldopay.companyservice.repository.merchant.MerchantRepository;
import com.eldopay.companyservice.repository.merchant.RatingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RatingService {
    private RatingRepository ratingRepository;
    private ProductService productService;

    private MerchantRepository merchantRepository;
    private  UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }


    public List<Rating> getProductRating(Long productId) throws ProductException {
        var  ratings = ratingRepository.findAll().stream()
                .filter(rating -> rating.getProduct().getId() == productId)
                .collect(Collectors.toList());
        return ratings;
    }


    public Rating createRating(RatingRequest req, Customer user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setCreatedAt(LocalDateTime.now());
        rating.setCustomer(user);
        rating.setProduct(product);
        return ratingRepository.save(rating);
    }

}
