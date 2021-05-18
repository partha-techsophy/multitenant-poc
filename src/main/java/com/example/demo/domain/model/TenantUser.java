package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder(toBuilder = true)
public class TenantUser {

    private BigInteger id;
    private String firstName;
    protected String lastName;

}
