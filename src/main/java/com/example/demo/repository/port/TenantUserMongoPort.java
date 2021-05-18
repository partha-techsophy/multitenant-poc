package com.example.demo.repository.port;

import com.example.demo.domain.model.TenantUser;
import com.example.demo.repository.ITenantUserDocumentRepository;
import com.example.demo.repository.document.TenantUserDocument;
import com.example.demo.util.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;

@Service
@AllArgsConstructor
public class TenantUserMongoPort implements ITenantUserPort{

    private final ITenantUserDocumentRepository repository;

    @Override
    public BigInteger addUser(TenantUser tenantUser) {

        TenantUserDocument document = TenantUserDocument.builder()
                .id(IdGenerator.getNextId())
                .firstName(tenantUser.getFirstName())
                .lastName(tenantUser.getLastName())
                .createdBY(BigInteger.ONE)
                .createdOn(Instant.now())
                .build();
        repository.save(document);

        return document.getId();
    }
}
