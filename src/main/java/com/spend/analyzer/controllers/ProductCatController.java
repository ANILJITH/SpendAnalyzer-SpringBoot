package com.spend.analyzer.controllers;

import com.spend.analyzer.models.ProductCategory;
import com.spend.analyzer.repositories.ProductCategoryRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.deepEquals;

@RestController
@RequestMapping("/api")
public class ProductCatController {

    private final ProductCategoryRepo productCategoryRepo;
    public ProductCatController(ProductCategoryRepo productCategoryRepo){
        this.productCategoryRepo = productCategoryRepo;
    }

    @PostMapping("category")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategory postCategory(@RequestBody ProductCategory productCategory){
        return productCategoryRepo.save(productCategory);
    }

    @PostMapping("categories")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductCategory> postCategories(@RequestBody List<ProductCategory> productCategories){
        return productCategoryRepo.saveAll(productCategories);
    }

    @GetMapping("categories")
    public List<ProductCategory> getCategories(){
        return productCategoryRepo.findAll();
    }

    @GetMapping("categories/{catIds}")
    public List<ProductCategory> getCategories(@PathVariable String catIds){
        List<String> listCatIds = asList(catIds.split(","));
        return productCategoryRepo.findAll(listCatIds);
    }

    @GetMapping("category/{catId}")
    public ResponseEntity<ProductCategory> getCategory(@PathVariable String catId){
        ProductCategory productCategory = productCategoryRepo.findOne(catId);
        if(productCategory == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(productCategory);
    }


}
