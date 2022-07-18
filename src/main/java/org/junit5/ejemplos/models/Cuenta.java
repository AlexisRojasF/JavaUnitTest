package org.junit5.ejemplos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Cuenta {
    private String persona;
    private BigDecimal saldo;
}
