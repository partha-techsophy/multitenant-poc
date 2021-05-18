package com.example.demo.repository;

import com.example.demo.repository.document.TenantUserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ITenantUserDocumentRepository extends MongoRepository<TenantUserDocument, BigInteger> {
}
