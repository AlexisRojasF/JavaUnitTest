package org.junit5.ejemplos.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void testNombreCuenta(){
        Cuenta cuenta = new Cuenta("Alexis",new BigDecimal("1000.12346"));

        String esperando = "alexis";
        String real = cuenta.getPersona();
        Assertions.assertEquals(esperando,real);
    }

}