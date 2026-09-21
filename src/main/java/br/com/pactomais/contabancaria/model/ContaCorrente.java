package br.com.pactomais.contabancaria.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import br.com.pactomais.contabancaria.exception.SaldoInsuficienteException;
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

    @Override
    public void sacar(BigDecimal valor) {
        BigDecimal disponivel = getSaldo().add(limite);

        if (valor.compareTo(disponivel) > 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        setSaldo(getSaldo().subtract(valor));
    }
}