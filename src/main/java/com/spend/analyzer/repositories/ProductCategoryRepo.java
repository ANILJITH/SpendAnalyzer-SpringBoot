package com.spend.analyzer.repositories;

import com.spend.analyzer.models.Person;
import com.spend.analyzer.models.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepo {
    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> saveAll(List<ProductCategory> productCategory);

    List<ProductCategory> findAll();

    List<ProductCategory> findAll(List<String> catId);

    ProductCategory findOne(String catId);
}
