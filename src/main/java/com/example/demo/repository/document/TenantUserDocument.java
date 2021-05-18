package com.example.demo.repository.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Document(collation = "TenantUser")
public class TenantUserDocument extends BaseDocument{
    @Id
    private BigInteger id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
