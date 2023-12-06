package com.eldopay.companyservice.controller.merchant;


import com.eldopay.companyservice.dtos.ApiResponse;
import com.eldopay.companyservice.exceptions.ProductException;
import com.eldopay.companyservice.models.shop.Product;
import com.eldopay.companyservice.repository.merchant.ProductRepository;
import com.eldopay.companyservice.services.merchant.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public class ProductController {
    private ProductService productService;
    private ProductRepository productRepository;


    @GetMapping("/id/{productId}")
    public ResponseEntity<Product>  getProductByIdHandler(@PathVariable long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return  new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<List<Product>>  getAllProductByMerchantHandler(@PathVariable long merchantId) throws ProductException {
        List<Product>  products = productService.getProductByMerchant(merchantId);
        return  new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @GetMapping("/merchant/{merchantId}/category/{categoryId}")
    public ResponseEntity<List<Product>>  getAllProductByMerchantAndCategoryHandler(@PathVariable long merchantId, @PathVariable long categoryId) throws ProductException {
        List<Product>  products = productService.getProductByMerchantAndCategory(merchantId,categoryId);
        return  new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>>  getAllProductByCategoryHandler(@PathVariable long categoryId) throws ProductException {
        List<Product> products = productService.findByProductCategory(categoryId);
        return  new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(
            @PathVariable Long productId,@RequestHeader("Authorization") String jwt) throws ProductException {
        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("product deleted successfully");
        res.setSuccess(true);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }
    }
