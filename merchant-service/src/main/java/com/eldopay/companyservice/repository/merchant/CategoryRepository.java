package com.eldopay.companyservice.repository.merchant;

import com.eldopay.companyservice.models.shop.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<ProductCategory,Long> {
    public ProductCategory findByName(String name);

@Query("select c from ProductCategory c Where c.name=:name And c.parentCategory.name=:parentCategoryName ")
   public ProductCategory findByNameAndParent(@Param("name")String name, @Param("parentCategoryName") String parentCategoryName);

}
