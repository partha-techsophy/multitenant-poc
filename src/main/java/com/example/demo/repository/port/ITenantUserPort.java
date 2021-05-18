package com.example.demo.repository.port;

import com.example.demo.domain.model.TenantUser;

import java.math.BigInteger;

public interface ITenantUserPort {

    BigInteger addUser(TenantUser tenantUser);
}
