package com.example.demo.application.rest;

import com.example.demo.application.port.ITenantUserServicePort;
import com.example.demo.application.request.CreateTenantUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * curl --location --request POST 'http://localhost:8080/tenant/users' \
 * --header 'X-Tenant: T2' \
 * --header 'Content-Type: application/json' \
 * --data-raw '{
 *     "firstName" : "User1",
 *     "lastName": "lastname"
 *
 * }'
 */
@RestController
@AllArgsConstructor
public class TenantUserController implements ITenantUserController {

    private final ITenantUserServicePort tenantUserServicePort;

    @Override
    public ResponseEntity<String> addUser(CreateTenantUserDTO userDTO) {

        BigInteger id = tenantUserServicePort.addUser(userDTO);

        return ResponseEntity.ok(String.valueOf(id));
    }
}
