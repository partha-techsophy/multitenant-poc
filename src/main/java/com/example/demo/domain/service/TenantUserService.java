package com.example.demo.domain.service;

import com.example.demo.domain.model.TenantUser;
import com.example.demo.repository.port.ITenantUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class TenantUserService implements ITenantUserService {

    private final ITenantUserPort tenantUserPort;

    @Override
    public BigInteger addUser(TenantUser tenantUser) {

        return tenantUserPort.addUser(tenantUser);
    }
}
