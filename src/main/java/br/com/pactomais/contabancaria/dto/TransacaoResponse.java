package br.com.pactomais.contabancaria.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import br.com.pactomais.contabancaria.model.Transacao;

public class TransacaoResponse {

    private Long id;
    private String tipo;
    private BigDecimal valor;
    private LocalDateTime data;

    public TransacaoResponse(Transacao transacao) {
        this.id = transacao.getId();
        this.tipo = transacao.getTipo().toString();
        this.valor = transacao.getValor();
        this.data = transacao.getData();
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }
}