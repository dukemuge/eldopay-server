package com.eldopay.companyservice.repository.county;

import com.eldopay.companyservice.models.county.CountyProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountyProductCategoryRepository extends JpaRepository<CountyProductCategory,Long> {
    public CountyProductCategory findByName(String name);

    @Query("select c from CountyProductCategory c Where c.name=:name And c.parentCategory.name=:parentCategoryName ")
    public CountyProductCategory findByNameAndParent(@Param("name")String name, @Param("parentCategoryName") String parentCategoryName);

}


