package com.example.demo.application.port;

import com.example.demo.application.request.CreateTenantUserDTO;
import com.example.demo.domain.model.TenantUser;
import com.example.demo.domain.service.ITenantUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class TenantUserServicePort implements ITenantUserServicePort {

    private final ITenantUserService tenanatUserService;

    @Override
    public BigInteger addUser(CreateTenantUserDTO dto) {
        return tenanatUserService.addUser(
                TenantUser.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build()
        );
    }
}
