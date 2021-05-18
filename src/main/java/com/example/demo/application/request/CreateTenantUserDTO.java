package com.example.demo.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Builder(toBuilder = true)
public class CreateTenantUserDTO {

    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    protected String lastName;
}
