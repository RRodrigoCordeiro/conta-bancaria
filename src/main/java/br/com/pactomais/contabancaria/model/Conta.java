package br.com.pactomais.contabancaria.model;

import java.math.BigDecimal;
import br.com.pactomais.contabancaria.exception.SaldoInsuficienteException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne
    private Correntista correntista;

    public Conta() {
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }

    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (valor.compareTo(this.saldo) > 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        this.saldo = this.saldo.subtract(valor);
    }
}