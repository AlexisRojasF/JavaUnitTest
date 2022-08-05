package org.junit5.ejemplos.models;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit5.ejemplos.exceptions.DineroInsifucienteException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    @Disabled
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12346"));

        String esperando = "ALEXIS";
        String real = cuenta.getPersona();
        assertEquals(esperando, real);
        assertTrue(real.equals("ALEXIS"));

    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12345"));
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("Alexis", new BigDecimal("1000.12345"));
        Cuenta cuenta2 = new Cuenta("Alexis", new BigDecimal("1000.12345"));

        //assertNotEquals(cuenta2,cuenta);
        assertEquals(cuenta2, cuenta);
    }

    @Test
    void testDebitoCuenta() {

        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {

        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDdineroInsuficienteExceptio() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsifucienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperando = "Dinero Insuficiente";

        assertEquals(esperando, actual);
    }

    @Test

    void Traferencia() {

        Cuenta origen = new Cuenta("Andres", new BigDecimal("3000.12345"));
        Cuenta destino = new Cuenta("Alexis", new BigDecimal("1000.12345"));

        Banco banco = new Banco();
        banco.setNombre("Bancolombia");
        banco.Transferir(origen, destino, new BigDecimal(3000));
        assertEquals("0.12345", origen.getSaldo().toPlainString());
        assertEquals("4000.12345", destino.getSaldo().toPlainString());
    }

    @Test
    @DisplayName("Aca va nombre personalizado de la funcion")
    void Cuentas() {

        Cuenta origen = new Cuenta("Andres", new BigDecimal("3000.12345"));
        Cuenta destino = new Cuenta("Alexis", new BigDecimal("1000.12345"));

        Banco banco = new Banco();
        banco.addCuenta(origen);
        banco.addCuenta(destino);


        banco.setNombre("Bancolombia");
        banco.Transferir(origen, destino, new BigDecimal(3000));
        assertAll(() -> assertEquals("0.12345", origen.getSaldo().toPlainString()),
                () -> assertEquals("4000.12345", destino.getSaldo().toPlainString()),
                () -> assertEquals(2, banco.getCuentas().size()),
                () -> assertEquals("Bancolombia", origen.getBanco().getNombre()),
                () -> assertEquals("Alexis", banco.getCuentas().stream()
                        .filter(c -> c.getPersona().equals("Alexis"))
                        .findFirst()
                        .get().getPersona(),"error personalizado"),
                () -> assertTrue(banco.getCuentas().stream()
                        .anyMatch(c -> c.getPersona().equals("Alexis")),"error personalizado"));


    }
}