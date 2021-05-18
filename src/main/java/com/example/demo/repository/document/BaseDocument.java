package com.example.demo.repository.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.Instant;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class BaseDocument implements Serializable {

    private BigInteger createdBY;
    private Instant createdOn;
    private BigInteger updatedBy;
    private Instant updatedOn;
    private BigInteger version;
}
