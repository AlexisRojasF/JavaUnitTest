package org.junit5.ejemplos.models;



import org.junit5.ejemplos.exceptions.DineroInsifucienteException;

import java.math.BigDecimal;


public class Cuenta {
    private String persona;
    private BigDecimal saldo;
    private Banco banco;

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cuenta)){
            return false;
        }

        Cuenta c = (Cuenta) obj;

        if (this.persona == null || this.saldo == null){
            return false;
        }

        return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
    }

    public String getPersona() {
        return persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void credito(BigDecimal monto){
        this.saldo = this.saldo.add(monto);


    }

    public void  debito(BigDecimal monto){

        BigDecimal nuevoSaldo = this.saldo.subtract(monto);

        if (nuevoSaldo.compareTo(BigDecimal.ZERO)<0){
            throw new DineroInsifucienteException("Dinero Insuficiente");
        }
    this.saldo = nuevoSaldo;

    }
}
