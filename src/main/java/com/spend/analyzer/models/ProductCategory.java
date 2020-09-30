package com.spend.analyzer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.bson.types.ObjectId;

import java.security.PrivateKey;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCategory {
    private ObjectId catId;
    private String catName;
    private char approvalFlag = 'N';
    private Date createdAt = new Date();

    public ObjectId getCatId() {
        return catId;
    }

    public ProductCategory setCatId(ObjectId catId) {
        this.catId = catId;
        return this;
    }

    public String getCatName() {
        return catName;
    }

    public ProductCategory setCatName(String catName) {
        this.catName = catName;
        return this;
    }

    public char getApprovalFlag() {
        return approvalFlag;
    }

    public ProductCategory setApprovalFlag(char approvalFlag) {
        this.approvalFlag = approvalFlag;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ProductCategory setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "catId=" + catId + ", catName=" + catName + ", approvalFlag=" + approvalFlag + ", createdAt=" + createdAt + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductCategory productCategory = (ProductCategory) o;
        return catId == productCategory.catId && Objects.equals(catName, productCategory.catName) && Objects.equals(approvalFlag,
                productCategory.approvalFlag) && Objects.equals(createdAt,
                productCategory.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catId, catName, approvalFlag, createdAt);
    }

}
