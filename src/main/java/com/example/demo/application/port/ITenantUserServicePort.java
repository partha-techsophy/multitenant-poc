package com.example.demo.application.port;

import com.example.demo.application.request.CreateTenantUserDTO;

import java.math.BigInteger;

public interface ITenantUserServicePort {

    BigInteger addUser(CreateTenantUserDTO dto);
}
