package com.example.demo.domain.service;

import com.example.demo.domain.model.TenantUser;

import java.math.BigInteger;

public interface ITenantUserService {

    BigInteger addUser(TenantUser tenantUser);
}
