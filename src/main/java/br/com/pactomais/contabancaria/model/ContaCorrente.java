package br.com.pactomais.contabancaria.model;

import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {


    private BigDecimal limite = BigDecimal.ZERO;

    public ContaCorrente() {
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}