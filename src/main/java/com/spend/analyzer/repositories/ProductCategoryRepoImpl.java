package com.spend.analyzer.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.spend.analyzer.models.ProductCategory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.eq;

@Repository
public class ProductCategoryRepoImpl implements ProductCategoryRepo{

    private static final TransactionOptions txnOptions = TransactionOptions.builder().readPreference(ReadPreference.primary()).readConcern(ReadConcern.MAJORITY).writeConcern(WriteConcern.MAJORITY).build();

    @Autowired
    private MongoClient client;
    private MongoCollection<ProductCategory> productCatCollection;

    @PostConstruct
    void init() {
        productCatCollection = client.getDatabase("SpendAnalyzer").getCollection("productcategory", ProductCategory.class);
    }


    @Override
    public ProductCategory save(ProductCategory productCategory) {
        productCategory.setCatId(new ObjectId());
        productCatCollection.insertOne(productCategory);
        return productCategory;
    }

    @Override
    public List<ProductCategory> saveAll(List<ProductCategory> productCategory) {
        try(ClientSession clientSession = client.startSession()){
            return clientSession.withTransaction(() -> {
                productCategory.forEach(p -> p.setCatId(new ObjectId()));
                productCatCollection.insertMany(clientSession,productCategory);
                return productCategory;
            },txnOptions);
        }
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCatCollection.find().into(new ArrayList<>());
    }

    @Override
    public List<ProductCategory> findAll(List<String> catId) {
        return productCatCollection.find(in("_id",mapToObjectIds(catId))).into(new ArrayList<>());
    }

    @Override
    public ProductCategory findOne(String catId) {
        return productCatCollection.find(eq("_id", new ObjectId(catId))).first();
    }

    private List<ObjectId> mapToObjectIds(List<String> ids) {
        return ids.stream().map(ObjectId::new).collect(Collectors.toList());
    }
}
