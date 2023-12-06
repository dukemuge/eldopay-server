package com.eldopay.companyservice.services.merchant;


import com.eldopay.companyservice.dtos.shop.CreateProductRequest;
import com.eldopay.companyservice.exceptions.ProductException;
import com.eldopay.companyservice.models.shop.Merchant;
import com.eldopay.companyservice.models.shop.Product;
import com.eldopay.companyservice.models.shop.ProductCategory;
import com.eldopay.companyservice.repository.merchant.CategoryRepository;
import com.eldopay.companyservice.repository.merchant.MerchantRepository;
import com.eldopay.companyservice.repository.merchant.ProductRepository;
import com.eldopay.companyservice.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService  {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserService userService;
    private MerchantRepository merchantRepository;


    public Product createProduct(CreateProductRequest req, Long  merchantId) {
        Merchant merchant =  merchantRepository.findById(merchantId).get();
        //find the  logged in merchant by Id
        ProductCategory topLevel  = categoryRepository.findByName(req.getTopLevelCategory());
        if(topLevel == null){
            ProductCategory topLevelCategory = new ProductCategory();
            topLevelCategory.setLevel(1);
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevel = categoryRepository.save(topLevelCategory);
        }
 ProductCategory secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
        if(secondLevel == null){
            ProductCategory secondLevelCategory = new ProductCategory();
            secondLevelCategory.setLevel(2);
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setName(req.getSecondLevelCategory());
            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        ProductCategory thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel == null){
            ProductCategory thirdLevelCategory = new ProductCategory();
            thirdLevelCategory.setLevel(3);
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setName(req.getThirdLevelCategory());
            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }

        Product product  = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPercent(req.getDiscountPercent());
        product.setPrice(req.getPrice());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setQuantity(req.getQuantity());
        product.setSizes(req.getSize());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());
//        product.setMerchant(req.getMerchantRequest());
        product.setMerchantId(merchant.getId());
        product.setMerchant(merchant);
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }


    public Product addToCatalog(){
        return null;
    }


    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product = findProductById(productId);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        }

        return productRepository.save(product);
    }


    public String deleteProduct(Long productId) throws ProductException {
        Product product  = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "product deleted successfully";
    }

    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new ProductException("product not found with id"+ id);
    }

    public List<Product> findByProductCategory(Long categoryId) {
        List<Product> products = productRepository.findAll();
        var filteredProducts = products.stream()
                .filter(product -> product.getCategory().getId() == categoryId)
                .collect(Collectors.toList());
        return filteredProducts;

    }

    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable  = PageRequest.of(pageNumber,pageSize);
        List<Product>  products = productRepository.findAll();
        int startIndex = (int) pageable.getOffset();
        int endIndex =Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product> pageContent = products.subList(startIndex,endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent,pageable, products.size());
        return filteredProducts;
    }


    public List<Product>getProductByMerchant(Long merchantId){
        Merchant merchant = merchantRepository.findById(merchantId).get();
        var products = productRepository.findAll();
        var filteredProducts =  products.stream().
                filter(product -> product.getMerchantId() == merchantId)
                .collect(Collectors.toList());
        return  filteredProducts;
    }

    public  List<Product> getProductByMerchantAndCategory(long merchantId,long categoryId){
            var allProducts = productRepository.findAll();
            var filteredProducts  = allProducts.stream()
                    .filter(product -> product.getMerchant().getId() == merchantId)
                    .filter(product -> product.getCategory().getId() == categoryId)
                    .collect(Collectors.toList());
            return filteredProducts;

    }


}
